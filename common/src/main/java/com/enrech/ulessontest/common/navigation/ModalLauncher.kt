package com.enrech.ulessontest.common.navigation

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.enrech.ulessontest.common.extension.getModalResult
import com.enrech.ulessontest.common_domain.entity.ModalResult

@Composable
inline fun <reified R: ModalResult> rememberModalLauncherForResult(crossinline onResult: (result: R) -> Unit) =
    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult(), onResult = {
        val data = it.data?.getModalResult<R>()
        if (it.resultCode == Activity.RESULT_OK && data != null ) {
            onResult(data)
        }
    })