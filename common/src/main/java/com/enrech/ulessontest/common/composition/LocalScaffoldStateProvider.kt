package com.enrech.ulessontest.common.composition

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.compositionLocalOf

val LocalScaffoldStateProvider = compositionLocalOf<ScaffoldState> {
    error("No LocalScaffoldStateProvider provided")
}