package com.enrech.ulessontest.common.composition

import androidx.activity.ComponentActivity
import androidx.compose.runtime.compositionLocalOf

val LocalParentActivity = compositionLocalOf<ComponentActivity> {
    error("No LocalParentActivity provided")
}