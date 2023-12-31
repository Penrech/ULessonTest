package com.enrech.ulessontest.downloads.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.enrech.ulessontest.common.navigation.uLessonMainComposable
import com.enrech.ulessontest.downloads.screens.DownloadScreen

fun NavGraphBuilder.downloadGraphGraph() {
    navigation(startDestination = DownloadDestinations.Main.routeDestination, route = DownloadDestinations.Root.routeDestination) {
        deepLink(DownloadDestinations.Root.deepLinkDestination)

        uLessonMainComposable(DownloadDestinations.Main) {
            DownloadScreen()
        }
    }
}