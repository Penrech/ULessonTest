package com.enrech.ulessontest.common.composition

import androidx.compose.runtime.compositionLocalOf
import com.enrech.ulessontest.common.navigation.FeatureModalNavigator
import com.enrech.ulessontest.common.navigation.FeatureNavigator
import com.enrech.ulessontest.common.navigation.IntentNavigator

val LocalFeatureNavigator = compositionLocalOf<FeatureNavigator> {
    error("No LocalFeatureNavigator provided")
}
val LocalModalFeatureNavigator = compositionLocalOf<FeatureModalNavigator> {
    error("No LocalModalFeatureNavigator provided")
}

val LocalIntentNavigator = compositionLocalOf<IntentNavigator> {
    error("No LocalIntentNavigator provided")
}
