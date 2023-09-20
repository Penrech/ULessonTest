package com.enrech.ulessontest.common.composition

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No LocalMainNavController provided")
}
