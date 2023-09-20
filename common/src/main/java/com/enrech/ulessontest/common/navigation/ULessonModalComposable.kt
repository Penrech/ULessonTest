package com.enrech.ulessontest.common.navigation

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.enrech.ulessontest.common.components.custom_bottom_sheet.customBottomSheet
import com.enrech.ulessontest.common.composition.LocalModalNavController
import com.enrech.ulessontest.common.navigation.ModalFeatureDestination.Companion.MODAL_BOTTOM_SHEET
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.uLessonBottomSheetModalComposable(
    destination: ModalFeatureDestination,
    onDismiss: () -> Unit = {},
    content: @Composable NavBackStackEntry.(modalNavArguments: ModalNavArguments, arguments: Bundle?) -> Unit
) {
    val route = destination.routeDestination
    val arguments = destination.getNavArguments().plus(navArgument(MODAL_BOTTOM_SHEET) {
        type = NavType.BoolType
        defaultValue = true
    })

    val deepLinks = listOf(navDeepLink { uriPattern = destination.deepLinkDestination })
    return customBottomSheet(route, arguments, deepLinks) {
        val localModalNavHostController = LocalModalNavController.current

        BackHandler {
            localModalNavHostController.popBackStack()
        }

        DisposableEffect(Unit) {
            onDispose { onDismiss() }
        }

        content(
            it,
            ModalNavArguments(
                it.arguments?.getString(ModalFeatureLauncher.MODAL_DESTINATION_ROUTE),
                it.arguments?.getString(ModalFeatureLauncher.MODAL_DESTINATION_KEY)
            ),
            it.arguments
        )
    }
}

fun NavGraphBuilder.uLessonNavigationModalComposable(
    destination: ModalFeatureDestination,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left
        )
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left
        )
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right
        )
    },
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right
        )
    },
    content: @Composable NavBackStackEntry.(modalNavArguments: ModalNavArguments, arguments: Bundle?) -> Unit
) {
    val route = destination.routeDestination
    val arguments = destination.getNavArguments()

    val deepLinks = listOf(navDeepLink { uriPattern = destination.deepLinkDestination })
    return composable(
        route,
        arguments,
        deepLinks,
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition
    ) {
        content(
            it,
            ModalNavArguments(
                it.arguments?.getString(ModalFeatureLauncher.MODAL_DESTINATION_ROUTE),
                it.arguments?.getString(ModalFeatureLauncher.MODAL_DESTINATION_KEY)
            ),
            it.arguments
        )
    }
}