package com.enrech.ulessontest.common.components.custom_bottom_sheet

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeProgress
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.navigation.FloatingWindow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.NavigatorState
import com.enrech.ulessontest.common.components.custom_bottom_sheet.CustomBottomSheetNavigator.Destination
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform

/**
 * The state of a [ModalBottomSheetLayout] that the [BottomSheetNavigator] drives
 *
 * @param sheetState The sheet state that is driven by the [BottomSheetNavigator]
 */
@ExperimentalMaterialNavigationApi
@OptIn(ExperimentalMaterialApi::class)
@Stable
public class CustomBottomSheetNavigatorSheetState(internal val sheetState: ModalBottomSheetState) {
    /**
     * @see ModalBottomSheetState.isVisible
     */
    public val isVisible: Boolean
        get() = sheetState.isVisible

    /**
     * @see ModalBottomSheetState.currentValue
     */
    public val currentValue: ModalBottomSheetValue
        get() = sheetState.currentValue

    /**
     * @see ModalBottomSheetState.targetValue
     */
    public val targetValue: ModalBottomSheetValue
        get() = sheetState.targetValue

    /**
     * @see ModalBottomSheetState.offset
     */
    @Deprecated(
        message = "BottomSheetNavigatorSheetState#offset has been removed",
        level = DeprecationLevel.ERROR
    )
    public val offset: State<Float>
        get() = error("BottomSheetNavigatorSheetState#offset has been removed")

    /**
     * @see ModalBottomSheetState.direction
     */
    @Deprecated(
        message = "BottomSheetNavigatorSheetState#direction has been removed",
        level = DeprecationLevel.ERROR
    )
    public val direction: Float
        get() = error("BottomSheetNavigatorSheetState#direction has been removed.")

    /**
     * @see ModalBottomSheetState.progress
     */

    public val progress: SwipeProgress<ModalBottomSheetValue>
        get() = SwipeProgress(from = currentValue, to = targetValue, fraction = sheetState.progress)
}

/**
 * Create and remember a [BottomSheetNavigator]
 */
@ExperimentalMaterialNavigationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun rememberCustomBottomSheetNavigator(
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    skipHalfExpanded: Boolean = true
): CustomBottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        animationSpec = animationSpec,
        skipHalfExpanded = skipHalfExpanded
    )
    return remember { CustomBottomSheetNavigator(sheetState) }
}


/**
 * Navigator that drives a [ModalBottomSheetState] for use of [ModalBottomSheetLayout]s
 * with the navigation library. Every destination using this Navigator must set a valid
 * [Composable] by setting it directly on an instantiated [Destination] or calling
 * [androidx.navigation.compose.material.bottomSheet].
 *
 * <b>The [sheetContent] [Composable] will always host the latest entry of the back stack. When
 * navigating from a [BottomSheetNavigator.Destination] to another
 * [BottomSheetNavigator.Destination], the content of the sheet will be replaced instead of a
 * new bottom sheet being shown.</b>
 *
 * When the sheet is dismissed by the user, the [state]'s [NavigatorState.backStack] will be popped.
 *
 * The primary constructor is not intended for public use. Please refer to
 * [rememberBottomSheetNavigator] instead.
 *
 * @param sheetState The [ModalBottomSheetState] that the [BottomSheetNavigator] will use to
 * drive the sheet state
 */
@ExperimentalMaterialNavigationApi
@OptIn(ExperimentalMaterialApi::class)
@Navigator.Name("BottomSheetNavigator")
public class CustomBottomSheetNavigator(
    internal val sheetState: ModalBottomSheetState
) : Navigator<CustomBottomSheetNavigator.Destination>() {

    private var attached by mutableStateOf(false)

    /**
     * Get the back stack from the [state]. In some cases, the [sheetContent] might be composed
     * before the Navigator is attached, so we specifically return an empty flow if we aren't
     * attached yet.
     */
    private val backStack: StateFlow<List<NavBackStackEntry>>
        get() = if (attached) {
            state.backStack
        } else {
            MutableStateFlow(emptyList())
        }

    /**
     * Get the transitionsInProgress from the [state]. In some cases, the [sheetContent] might be
     * composed before the Navigator is attached, so we specifically return an empty flow if we
     * aren't attached yet.
     */
    internal val transitionsInProgress: StateFlow<Set<NavBackStackEntry>>
        get() = if (attached) {
            state.transitionsInProgress
        } else {
            MutableStateFlow(emptySet())
        }

    /**
     * Access properties of the [ModalBottomSheetLayout]'s [ModalBottomSheetState]
     */
    public val navigatorSheetState: CustomBottomSheetNavigatorSheetState = CustomBottomSheetNavigatorSheetState(sheetState)

    /**
     * A [Composable] function that hosts the current sheet content. This should be set as
     * sheetContent of your [ModalBottomSheetLayout].
     */
    public val sheetContent: @Composable ColumnScope.() -> Unit = {
        val saveableStateHolder = rememberSaveableStateHolder()
        val transitionsInProgressEntries by transitionsInProgress.collectAsState()

        // The latest back stack entry, retained until the sheet is completely hidden
        // While the back stack is updated immediately, we might still be hiding the sheet, so
        // we keep the entry around until the sheet is hidden
        val retainedEntry by produceState<NavBackStackEntry?>(
            initialValue = null,
            key1 = backStack
        ) {
            backStack
                .transform { backStackEntries ->
                    // Always hide the sheet when the back stack is updated
                    // Regardless of whether we're popping or pushing, we always want to hide
                    // the sheet first before deciding whether to re-show it or keep it hidden
                    try {
                        sheetState.hide()
                    } catch (_: CancellationException) {
                        // We catch but ignore possible cancellation exceptions as we don't want
                        // them to bubble up and cancel the whole produceState coroutine
                    } finally {
                        emit(backStackEntries.lastOrNull())
                    }
                }
                .collect {
                    value = it
                }
        }

        if (retainedEntry != null) {
            LaunchedEffect(retainedEntry) {
                sheetState.show()
            }
        }

        BackHandler(retainedEntry != null) {
            state.popWithTransition(retainedEntry!!, false)
        }

        SheetContentHost(
            backStackEntry = retainedEntry,
            sheetState = sheetState,
            saveableStateHolder = saveableStateHolder,
            onSheetShown = {
                transitionsInProgressEntries.forEach(state::markTransitionComplete)
            },
            onSheetDismissed = { backStackEntry ->
                // Sheet dismissal can be started through popBackStack in which case we have a
                // transition that we'll want to complete
                if (transitionsInProgressEntries.contains(backStackEntry)) {
                    state.markTransitionComplete(backStackEntry)
                }
                // If there is no transition in progress, the sheet has been dimissed by the
                // user (for example by tapping on the scrim or through an accessibility action)
                // In this case, we will immediately pop without a transition as the sheet has
                // already been hidden
                else {
                    state.pop(popUpTo = backStackEntry, saveState = false)
                }
            }
        )
    }

    override fun onAttach(state: NavigatorState) {
        super.onAttach(state)
        attached = true
    }

    override fun createDestination(): Destination = Destination(
        navigator = this,
        content = {}
    )

    @SuppressLint("NewApi") // b/187418647
    override fun navigate(
        entries: List<NavBackStackEntry>,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ) {
        entries.forEach { entry ->
            state.pushWithTransition(entry)
        }
    }

    override fun popBackStack(popUpTo: NavBackStackEntry, savedState: Boolean) {
        state.popWithTransition(popUpTo, savedState)
    }

    /**
     * [NavDestination] specific to [BottomSheetNavigator]
     */
    @NavDestination.ClassType(Composable::class)
    public class Destination(
        navigator: CustomBottomSheetNavigator,
        internal val content: @Composable ColumnScope.(NavBackStackEntry) -> Unit
    ) : NavDestination(navigator), FloatingWindow
}