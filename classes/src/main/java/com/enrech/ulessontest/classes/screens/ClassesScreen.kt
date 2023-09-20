package com.enrech.ulessontest.classes.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.ulessontest.common.components.utils.TextValue
import com.enrech.ulessontest.common.screen.MockScreen

@Composable
fun ClassesScreen(viewModel: ClassesViewModel = hiltViewModel()) {
    UIContent()
}

@Composable
private fun UIContent() {
    MockScreen(text = TextValue("Classes Screen"))
}

@Preview(showBackground = true, name = "Classes Template")
@Composable
private fun ClassesPreview() {
    UIContent()
}