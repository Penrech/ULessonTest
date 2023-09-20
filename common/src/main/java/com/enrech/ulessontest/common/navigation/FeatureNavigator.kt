package com.enrech.ulessontest.common.navigation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.core.net.toUri
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import com.enrech.ulessontest.common.extension.routeName
import timber.log.Timber

@SuppressLint("RestrictedApi")
class FeatureNavigator(private val navController: NavController) {

    fun openDeepLink(uri: String) {
        try {
            navigationToDestination(uri.toUri())
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
        }
    }

    fun openDestination(destination: Destination, removePrevious: Boolean = false) =
        when(removePrevious) {
            true -> navigationToDestinationCleaningBackStack(destination.routeDestinationWithParams)
            false -> navigationToDestination(destination.routeDestinationWithParams)
        }

    fun goBack() = navController.navigateUp()

    fun containsDestination(destination: Destination): Boolean =
        navController.currentBackStack.value.any { it.destination.route == destination.routeDestination }

    fun containsRouteName(routeName: String): Boolean =
        navController.currentBackStack.value.any { it.destination.route?.contains(routeName) ?: false }

    fun currentRoute(): String? = navController.currentBackStackEntry?.destination?.route

    fun openDestinationOrBackToIt(destination: Destination, destinationToBeForcedDestroyed: Destination? = null, removePrevious: Boolean = false) {
        destinationToBeForcedDestroyed?.let {
            popUntilDestination(it)
            openDestinationOrBackToIt(destination, removePrevious)
        } ?: openDestinationOrBackToIt(destination, removePrevious)
    }

    fun openDestinationOrBackToPrevious(destination: Destination, shouldBackToPrevious: (arguments: Map<String, NavArgument>) -> Boolean = { true }) {
        val backSize = navController.currentBackStack.value.size
        val previousNavBackStackEntry = navController.currentBackStack.value.getOrNull(backSize - 1)
        previousNavBackStackEntry?.let {
            if (shouldBackToPrevious(it.destination.arguments)) {
                navController.popBackStack()
                navController.navigate(destination.routeDestinationWithParams) {
                    popUpTo(it.destination.id) {
                        inclusive = true
                    }
                }
            } else {
                openDestination(destination)
            }
        } ?: openDestination(destination)
    }

    private fun openDestinationOrBackToIt(destination: Destination, removePrevious: Boolean = false) {
        if (!popUntilDestination(destination)) {
            openDestination(destination, removePrevious)
        }
    }

    private fun popUntilDestination(destination: Destination): Boolean =
        navController.currentBackStack.value.firstOrNull { it.destination.routeName == destination.routeName }?.let {
            navController.popBackStack(it.destination.id, false)
        } ?: false

    fun <T: FeatureResultData> goBackWithResult(result: T, key: String = FeatureResultData.FEATURE_RESULT) {
        navController
            .previousBackStackEntry
            ?.savedStateHandle
            ?.set(key, result)
        goBack()
    }

    private fun navigationToDestination(uri: Uri) = navController.navigate(uri)
    private fun navigationToDestinationCleaningBackStack(route: String) =
        navController.navigate(route) {
            navController.currentBackStackEntry?.destination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    private fun navigationToDestination(route: String) = navController.navigate(route = route)
}