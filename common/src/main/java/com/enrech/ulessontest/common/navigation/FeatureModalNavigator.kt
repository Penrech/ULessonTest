package com.enrech.ulessontest.common.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.enrech.ulessontest.common.extension.popCompleteStack
import com.enrech.ulessontest.common.extension.routeName

@SuppressLint("RestrictedApi")
class FeatureModalNavigator(
    private val modalNavController: NavController,
    private val mainNavController: NavController
) {

    fun popCompleteStack() = modalNavController.popCompleteStack()

    fun openDestination(destination: ModalFeatureDestination) {
        navigationToDestination(destination.routeDestinationWithParams)
    }

    fun openMainDestinationFromModal(destination: Destination) {
        modalNavController.popCompleteStack()
        mainNavController.navigate(destination.routeDestinationWithParams) {
            popUpTo(mainNavController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun openDestinationOrBackToIt(destination: ModalFeatureDestination, destinationToBeForcedDestroyed: ModalFeatureDestination? = null, removePrevious: Boolean = false) {
        destinationToBeForcedDestroyed?.let {
            popUntilDestination(it)
            openDestinationOrBackToIt(destination, removePrevious)
        } ?: openDestinationOrBackToIt(destination, removePrevious)
    }

    fun openDestination(destination: ModalFeatureDestination, removePrevious: Boolean = false) =
        when (removePrevious) {
            true -> navigationToDestinationCleaningBackStack(destination.routeDestinationWithParams)
            false -> navigationToDestination(destination.routeDestinationWithParams)
        }

    private fun navigationToDestination(route: String) = modalNavController.navigate(route = route) {
        if (modalNavController.currentBackStack.value.size > 2) {
            val previousBottomSheet = modalNavController.currentBackStack.value.lastOrNull { it.destination !is NavGraph }
            previousBottomSheet?.let {
                if (it.arguments?.getBoolean(ModalFeatureDestination.MODAL_BOTTOM_SHEET) == true) {
                    modalNavController.popBackStack(it.destination.id, true)
                }
            }
        }
    }

    private fun navigationToDestinationCleaningBackStack(route: String) =
        modalNavController.navigate(route) {
            modalNavController.currentBackStackEntry?.destination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }

    private fun openDestinationOrBackToIt(destination: ModalFeatureDestination, removePrevious: Boolean = false) {
        if (!popUntilDestination(destination)) {
            openDestination(destination, removePrevious)
        }
    }

    private fun popUntilDestination(destination: ModalFeatureDestination): Boolean =
        modalNavController.currentBackStack.value.firstOrNull { it.destination.routeName == destination.routeName }?.let {
            modalNavController.popBackStack(it.destination.id, false)
        } ?: false
}
