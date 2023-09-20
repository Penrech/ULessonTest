package com.enrech.ulessontest.common_resources.extensions

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.enrech.ulessontest.common_resources.theme.ULessonTypography
import com.enrech.ulessontest.common_resources.theme.mplusFontFamily

val Typography.headlineSmallAlt: TextStyle get() = ULessonTypography.headlineMedium.copy(fontSize = 24.sp, lineHeight = 32.sp)
val Typography.titleMediumAlt: TextStyle get() = ULessonTypography.titleMedium.copy(fontSize = 18.sp, lineHeight = 26.sp, fontFamily = mplusFontFamily)
val Typography.labelLargeAlt: TextStyle get() = ULessonTypography.labelMedium.copy(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.2.sp)