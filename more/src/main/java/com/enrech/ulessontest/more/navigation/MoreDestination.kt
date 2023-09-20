package com.enrech.ulessontest.more.navigation

import com.enrech.ulessontest.common.navigation.Destination

sealed class MoreDestinations: Destination {
    data object Root: MoreDestinations() {
        override val routeName: String = "more_root"
    }
    data object Main: MoreDestinations() {
        override val routeName: String = "more_main"
    }
}