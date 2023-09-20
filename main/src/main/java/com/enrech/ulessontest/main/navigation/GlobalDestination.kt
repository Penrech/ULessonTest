package com.enrech.ulessontest.main.navigation

import com.enrech.ulessontest.common.navigation.Destination

sealed class GlobalDestination: Destination {
    data object Root: GlobalDestination() {
        override val routeName: String = "global_root"
    }
}