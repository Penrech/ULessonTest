package com.enrech.ulessontest.classes.navigation

import com.enrech.ulessontest.common.navigation.Destination

sealed class ClassesDestinations: Destination {
    data object Root: ClassesDestinations() {
        override val routeName: String = "classes_root"
    }
    data object Main: ClassesDestinations() {
        override val routeName: String = "classes_main"
    }
}