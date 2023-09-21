package com.enrech.ulessontest.main.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.common.composition.LocalParentActivity
import com.enrech.ulessontest.common.composition.LocalScaffoldStateProvider
import com.enrech.ulessontest.common_resources.theme.ULessonTheme
import com.enrech.ulessontest.main.navigation.MainBottomNavigation
import com.enrech.ulessontest.main.navigation.MainNavHost

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()

    val mainNavController = LocalNavController.current
    val activity = LocalParentActivity.current

    ULessonTheme {
        CompositionLocalProvider(
            LocalScaffoldStateProvider provides scaffoldState,
        ) {
            UIContent(
                scaffoldState,
                mainNavController,
            )
        }
    }
}

@Composable
private fun UIContent(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            MainBottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        MainNavHost(modifier = Modifier.padding(innerPadding))
    }
}