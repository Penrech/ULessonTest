package com.enrech.ulessontest.more.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.ulessontest.common.components.utils.TextValue
import com.enrech.ulessontest.common.screen.MockScreen

@Composable
fun MoreScreen(viewModel: MoreViewModel = hiltViewModel()) {
    UIContent()
}

@Composable
private fun UIContent() {
    MockScreen(text = TextValue("More Screen"))
}

@Preview(showBackground = true, name = "More Template")
@Composable
private fun MorePreview() {
    UIContent()
}