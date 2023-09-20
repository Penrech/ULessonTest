package com.enrech.ulessontest.classes.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.enrech.ulessontest.classes.screens.ClassesScreen
import com.enrech.ulessontest.common.navigation.uLessonComposable

fun NavGraphBuilder.classesGraphGraph() {
    navigation(startDestination = ClassesDestinations.Main.routeDestination, route = ClassesDestinations.Root.routeDestination) {
        deepLink(ClassesDestinations.Root.deepLinkDestination)

        uLessonComposable(ClassesDestinations.Main) {
            ClassesScreen()
        }
    }
}