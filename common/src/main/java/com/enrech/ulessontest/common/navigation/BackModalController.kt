package com.enrech.ulessontest.common.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.enrech.ulessontest.common.composition.LocalModalNavController
import com.enrech.ulessontest.common.composition.LocalNavController

class BackModalController(private val modalNav: NavHostController, private val featureNav: NavHostController) {
    fun goBack() {
        if (destinationCountOnBackStack == 1) {
            modalNav.popBackStack()
        } else {
            featureNav.navigateUp()
        }
    }

    private val destinationCountOnBackStack: Int
        @SuppressLint("RestrictedApi")
        get() = featureNav.currentBackStack.value.count { entry ->
            entry.destination !is NavGraph
        }
}

@Composable
fun rememberBackNavController(): BackModalController {
    val modalNav = LocalModalNavController.current
    val featureNav = LocalNavController.current

    return remember(modalNav, featureNav) {
        BackModalController(modalNav, featureNav)
    }
}