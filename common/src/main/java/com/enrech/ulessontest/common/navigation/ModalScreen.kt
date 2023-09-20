package com.enrech.ulessontest.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.enrech.ulessontest.common.components.toolbar.ULessonNavigationToolbar
import com.enrech.ulessontest.common.composition.LocalFeatureNavigator
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.common.composition.LocalScaffoldStateProvider
import com.enrech.ulessontest.common_resources.theme.ULessonTheme

@Composable
fun <T : Destination> ModalScreen(
    startDestination: T,
    navHostContent: @Composable (padding: PaddingValues, initDestination: T) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val backModalController = rememberBackNavController()

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalFeatureNavigator provides FeatureNavigator(navController),
        LocalScaffoldStateProvider provides scaffoldState,
    ) {
        ULessonTheme {
            UIContent(
                startDestination,
                navHostContent,
                scaffoldState,
                backModalController
            )
        }
    }
}

@Composable
private fun <T : Destination> UIContent(
    startDestination: T,
    navHostContent: @Composable (padding: PaddingValues, initDestination: T) -> Unit,
    scaffoldState: ScaffoldState,
    backModalController: BackModalController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ULessonNavigationToolbar { backModalController.goBack() }
        },
        backgroundColor = Color.Transparent
    ) { innerPadding -> navHostContent(innerPadding, startDestination) }
}
