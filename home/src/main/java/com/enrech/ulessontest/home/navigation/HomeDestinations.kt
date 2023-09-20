package com.enrech.ulessontest.home.navigation

import com.enrech.ulessontest.common.navigation.Destination

sealed class HomeDestinations : Destination {
    data object Root : HomeDestinations() {
        override val routeName: String = "home_root"
    }

    data object Main : HomeDestinations() {
        override val routeName: String = "home_main"
    }
}

