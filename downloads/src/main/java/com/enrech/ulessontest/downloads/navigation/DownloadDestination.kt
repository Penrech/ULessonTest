package com.enrech.ulessontest.downloads.navigation

import com.enrech.ulessontest.common.navigation.Destination

sealed class DownloadDestinations: Destination {
    data object Root: DownloadDestinations() {
        override val routeName: String = "download_root"
    }
    data object Main: DownloadDestinations() {
        override val routeName: String = "download_main"
    }
}