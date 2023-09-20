package com.enrech.ulessontest.common.composition

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalModalNavController = compositionLocalOf<NavHostController> {
    error("No LocalModalNavController provided")
}