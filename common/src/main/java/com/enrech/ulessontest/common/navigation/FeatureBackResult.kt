package com.enrech.ulessontest.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.common.navigation.FeatureResultData.Companion.FEATURE_RESULT

@Composable
inline fun <reified R: FeatureResultData> rememberFeatureNavigationForResult(
    key: String = FEATURE_RESULT,
    crossinline onResult: (result: R) -> Unit
) {
    val navController = LocalNavController.current
    val currentBackStackEntry = navController.currentBackStackEntry

    val resultData =
        currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<R>(key)
            ?.observeAsState()

    LaunchedEffect(resultData?.value) {
        resultData?.value?.let(onResult)
        currentBackStackEntry?.savedStateHandle?.set(key, null)
    }
}