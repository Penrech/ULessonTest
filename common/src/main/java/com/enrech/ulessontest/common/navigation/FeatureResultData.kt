package com.enrech.ulessontest.common.navigation

import android.os.Parcelable

interface FeatureResultData: Parcelable {
    companion object {
        const val FEATURE_RESULT = "destination_result"
    }
}