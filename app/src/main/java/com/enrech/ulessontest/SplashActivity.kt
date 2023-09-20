package com.enrech.ulessontest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.enrech.ulessontest.common.composition.LocalFeatureNavigator
import com.enrech.ulessontest.common.composition.LocalIntentNavigator
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.common.composition.LocalParentActivity
import com.enrech.ulessontest.common.navigation.FeatureNavigator
import com.enrech.ulessontest.common.navigation.IntentNavigator
import com.enrech.ulessontest.common_resources.theme.ULessonTheme
import com.enrech.ulessontest.screens.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ULessonTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalIntentNavigator provides IntentNavigator(this),
                    LocalNavController provides navController,
                    LocalFeatureNavigator provides FeatureNavigator(navController),
                    LocalParentActivity provides this,
                ) {
                    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)) {
                        SplashScreen()
                    }
                }
            }
        }
    }
}