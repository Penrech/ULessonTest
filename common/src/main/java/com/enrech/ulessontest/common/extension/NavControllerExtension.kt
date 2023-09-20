package com.enrech.ulessontest.common.extension

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enrech.ulessontest.common.navigation.ModalFeatureResult
import com.enrech.ulessontest.common.navigation.ModalFeatureResultData
import com.enrech.ulessontest.common.navigation.ModalFeatureResultData.Companion.MODAL_FEATURE_RESULT
import com.enrech.ulessontest.common.navigation.ModalNavArguments

fun NavHostController.backWithModalResult(
    modalNavArguments: ModalNavArguments,
    result: ModalFeatureResultData
) {
    modalNavArguments.originId?.let {
        previousBackStackEntry
            ?.savedStateHandle
            ?.set(
                MODAL_FEATURE_RESULT,
                ModalFeatureResult(it, result, modalNavArguments.key ?: result::class.java.name)
            )
    }
    popBackStack()
}

@SuppressLint("RestrictedApi")
fun NavController.popCompleteStack() {
    repeat(this.currentBackStack.value.size) {
        this.navigateUp()
    }
}