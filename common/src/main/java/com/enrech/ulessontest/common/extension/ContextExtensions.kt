package com.enrech.ulessontest.common.extension

import android.content.Context
import com.enrech.ulessontest.common.navigation.IntentNavigator

val Context.intentNavigator get() = IntentNavigator(this)
