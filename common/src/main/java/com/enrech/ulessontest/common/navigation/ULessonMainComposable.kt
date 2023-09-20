package com.enrech.ulessontest.common.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink

fun NavGraphBuilder.uLessonMainComposable(
    destination: Destination,
    forceAnimations: Boolean = false,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    val route = destination.routeDestination
    val arguments = destination.getNavArguments(withExtras = true)

    val deepLinks = listOf(navDeepLink { uriPattern = destination.deepLinkDestination })

    return if (forceAnimations) {
        composable(route, arguments, deepLinks, enterTransition, exitTransition, popEnterTransition, popExitTransition, content)
    } else {
        composable(
            route, arguments, deepLinks,
            enterTransition = {
                when (isDifferentRoot(initialState.destination, targetState.destination)) {
                    true -> EnterTransition.None
                    false -> enterTransition?.invoke(this)
                }
            },
            exitTransition = {
                when (isDifferentRoot(initialState.destination, targetState.destination)) {
                    true -> ExitTransition.None
                    false -> exitTransition?.invoke(this)
                }
            },
            popEnterTransition = {
                when (isDifferentRoot(initialState.destination, targetState.destination)) {
                    true -> EnterTransition.None
                    false -> popEnterTransition?.invoke(this)
                }
            },
            popExitTransition = {
                when (isDifferentRoot(initialState.destination, targetState.destination)) {
                    true -> ExitTransition.None
                    false -> popExitTransition?.invoke(this)
                }
            }, content
        )
    }
}

private fun isDifferentRoot(
    initDestination: NavDestination,
    targetDestination: NavDestination
): Boolean {
    val initHierarchy = initDestination.hierarchy.toList()
    val lastInitElement = initHierarchy.lastIndex
    val targetHierarchy = targetDestination.hierarchy.toList()
    val lastTargetElement = targetHierarchy.lastIndex

    return initHierarchy.getOrNull(lastInitElement - 1)?.route != targetHierarchy.getOrNull(lastTargetElement - 1)?.route
}
