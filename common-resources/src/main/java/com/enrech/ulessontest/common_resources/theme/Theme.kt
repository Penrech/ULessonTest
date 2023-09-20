package com.enrech.ulessontest.common_resources.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ULessonTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = ULessonTypography,
        content = content
    )
}

