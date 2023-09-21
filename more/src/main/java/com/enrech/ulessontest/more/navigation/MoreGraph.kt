package com.enrech.ulessontest.more.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.enrech.ulessontest.common.navigation.uLessonMainComposable
import com.enrech.ulessontest.more.screens.MoreScreen

fun NavGraphBuilder.moreGraphGraph() {
    navigation(startDestination = MoreDestinations.Main.routeDestination, route = MoreDestinations.Root.routeDestination) {
        deepLink(MoreDestinations.Root.deepLinkDestination)

        uLessonMainComposable(MoreDestinations.Main) {
            MoreScreen()
        }
    }
}