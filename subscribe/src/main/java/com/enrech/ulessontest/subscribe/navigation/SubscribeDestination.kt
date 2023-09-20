package com.enrech.ulessontest.subscribe.navigation

import com.enrech.ulessontest.common.navigation.Destination

sealed class SubscribeDestinations: Destination {
    data object Root: SubscribeDestinations() {
        override val routeName: String = "subscribe_root"
    }
    data object Main: SubscribeDestinations() {
        override val routeName: String = "subscribe_main"
    }
}