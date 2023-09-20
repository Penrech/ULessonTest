package com.enrech.ulessontest.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import com.enrech.ulessontest.common.composition.LocalNavController

@Composable
fun <T: Destination> ModalNavHost(modifier: Modifier = Modifier, startDestination: T, builder: NavGraphBuilder.() -> Unit) {
    val mainNavController = LocalNavController.current

    NavHost(
        navController = mainNavController,
        startDestination = startDestination.routeDestination,
        modifier = modifier
    ) { builder() }
}