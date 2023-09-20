package com.enrech.ulessontest.more.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.enrech.ulessontest.common.navigation.uLessonComposable
import com.enrech.ulessontest.more.screens.MoreScreen

fun NavGraphBuilder.moreGraphGraph() {
    navigation(startDestination = MoreDestinations.Main.routeDestination, route = MoreDestinations.Root.routeDestination) {
        deepLink(MoreDestinations.Root.deepLinkDestination)

        uLessonComposable(MoreDestinations.Main) {
            MoreScreen()
        }
    }
}