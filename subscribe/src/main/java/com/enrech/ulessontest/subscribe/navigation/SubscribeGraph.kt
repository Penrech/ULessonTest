package com.enrech.ulessontest.subscribe.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.enrech.ulessontest.common.navigation.uLessonComposable
import com.enrech.ulessontest.subscribe.screens.SubscribeScreen

fun NavGraphBuilder.subscribeGraphGraph() {
    navigation(startDestination = SubscribeDestinations.Main.routeDestination, route = SubscribeDestinations.Root.routeDestination) {
        deepLink(SubscribeDestinations.Root.deepLinkDestination)

        uLessonComposable(SubscribeDestinations.Main) {
            SubscribeScreen()
        }
    }
}