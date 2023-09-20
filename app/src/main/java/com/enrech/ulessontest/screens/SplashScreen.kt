package com.enrech.ulessontest.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.ulessontest.common.components.utils.TextValue
import com.enrech.ulessontest.common.composition.LocalIntentNavigator
import com.enrech.ulessontest.common.screen.MockScreen
import com.enrech.ulessontest.common.utils.HandleEffects

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
    val intentNavigator = LocalIntentNavigator.current

    HandleEffects(viewModel.effect) {
        when(it) {
            SplashEffect.LaunchMainActivity -> intentNavigator.openMain()
        }
    }

    UIContent()
}

@Composable
private fun UIContent() {
    MockScreen(text = TextValue("Splash Screen"))
}

@Preview(showBackground = true, name = "Splash Template")
@Composable
private fun SplashPreview() {
    UIContent()
}