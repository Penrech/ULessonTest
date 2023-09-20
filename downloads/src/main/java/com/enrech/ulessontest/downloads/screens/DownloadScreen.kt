package com.enrech.ulessontest.downloads.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.enrech.ulessontest.common.components.utils.TextValue
import com.enrech.ulessontest.common.screen.MockScreen

@Composable
fun DownloadScreen(viewModel: DownloadViewModel = hiltViewModel()) {
    UIContent()
}

@Composable
private fun UIContent() {
    MockScreen(text = TextValue("Download Screen"))
}

@Preview(showBackground = true, name = "Download Template")
@Composable
private fun DownloadPreview() {
    UIContent()
}