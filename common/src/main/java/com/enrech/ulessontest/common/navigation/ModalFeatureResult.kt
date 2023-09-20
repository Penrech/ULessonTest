package com.enrech.ulessontest.common.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModalFeatureResult(
    val originRoute: String,
    val data: ModalFeatureResultData,
    val key: String = data::class.java.name
): Parcelable
