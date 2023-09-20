package com.enrech.ulessontest.subscribe.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.ulessontest.common.components.utils.TextValue
import com.enrech.ulessontest.common.screen.MockScreen

@Composable
fun SubscribeScreen(viewModel: SubscribeViewModel = hiltViewModel()) {
    UIContent()
}

@Composable
private fun UIContent() {
    MockScreen(text = TextValue("Subscribe Screen"))
}

@Preview(showBackground = true, name = "Subscribe Template")
@Composable
private fun SubscribePreview() {
    UIContent()
}