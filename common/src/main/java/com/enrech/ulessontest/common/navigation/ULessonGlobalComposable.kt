package com.enrech.ulessontest.common.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.common.navigation.ModalFeatureResultData.Companion.MODAL_FEATURE_RESULT
import com.enrech.ulessontest.common_domain.extension.ifLet
import timber.log.Timber

fun NavGraphBuilder.uLessonGlobalComposable(
    destination: Destination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    val route = destination.routeDestination
    val arguments = destination.getNavArguments()

    val deepLinks = listOf(navDeepLink { uriPattern = destination.deepLinkDestination })
    return composable(
        route,
        arguments,
        deepLinks,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
    ) {
        val mainNavController = LocalNavController.current

        val modalResultData = it
            .savedStateHandle
            .getLiveData<ModalFeatureResult>(MODAL_FEATURE_RESULT)
            .observeAsState()

        LaunchedEffect(modalResultData.value) {
            ifLet(modalResultData.value?.originRoute, modalResultData.value?.key) { originRoute, key ->
                try {
                    mainNavController.getBackStackEntry(originRoute)
                        .savedStateHandle[key] = modalResultData.value?.data
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
            it.savedStateHandle[MODAL_FEATURE_RESULT] = null
        }
        content(it)
    }
}