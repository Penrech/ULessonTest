package com.enrech.ulessontest.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.ulessontest.common.components.utils.TextValue
import com.enrech.ulessontest.common.screen.MockScreen

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    UIContent()
}

@Composable
private fun UIContent() {
    MockScreen(text = TextValue("Home Screen"))
}

@Preview(showBackground = true, name = "Home Template")
@Composable
private fun HomePreview() {
    UIContent()
}