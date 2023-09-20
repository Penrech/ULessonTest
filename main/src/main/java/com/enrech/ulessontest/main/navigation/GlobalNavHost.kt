package com.enrech.ulessontest.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.enrech.ulessontest.common.components.custom_bottom_sheet.CustomBottomSheetNavigator
import com.enrech.ulessontest.common.components.custom_bottom_sheet.CustomModalBottomSheetLayout
import com.enrech.ulessontest.common.composition.LocalModalNavController
import com.enrech.ulessontest.common.navigation.uLessonGlobalComposable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun GlobalNavHost(bottomSheetNavigator: CustomBottomSheetNavigator) {
    val modalNavController = LocalModalNavController.current

    CustomModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        NavHost(
            navController = modalNavController,
            startDestination = GlobalDestination.Root.routeDestination
        ) {
            uLessonGlobalComposable(GlobalDestination.Root) {

            }
        }
    }
}