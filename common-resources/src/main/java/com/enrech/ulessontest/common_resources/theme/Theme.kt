package com.enrech.ulessontest.common_resources.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun ULessonTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(background = grey20, onBackground = grey100),
        typography = ULessonTypography,
        content = content
    )
}

