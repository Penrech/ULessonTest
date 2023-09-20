package com.enrech.ulessontest.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.enrech.ulessontest.common.navigation.uLessonMainComposable
import com.enrech.ulessontest.home.screen.HomeScreen

fun NavGraphBuilder.homeGraphGraph() {
    navigation(
        startDestination = HomeDestinations.Main.routeDestination,
        route = HomeDestinations.Root.routeDestination
    ) {
        deepLink(HomeDestinations.Root.deepLinkDestination)

        uLessonMainComposable(HomeDestinations.Main) {
            HomeScreen()
        }
    }
}