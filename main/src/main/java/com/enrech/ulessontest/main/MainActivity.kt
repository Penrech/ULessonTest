package com.enrech.ulessontest.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enrech.ulessontest.common.components.custom_bottom_sheet.rememberCustomBottomSheetNavigator
import com.enrech.ulessontest.common.composition.LocalFeatureNavigator
import com.enrech.ulessontest.common.composition.LocalIntentNavigator
import com.enrech.ulessontest.common.composition.LocalModalFeatureNavigator
import com.enrech.ulessontest.common.composition.LocalModalNavController
import com.enrech.ulessontest.common.composition.LocalNavController
import com.enrech.ulessontest.common.composition.LocalParentActivity
import com.enrech.ulessontest.common.navigation.FeatureModalNavigator
import com.enrech.ulessontest.common.navigation.FeatureNavigator
import com.enrech.ulessontest.common.navigation.IntentNavigator
import com.enrech.ulessontest.common_resources.theme.ULessonTheme
import com.enrech.ulessontest.main.navigation.GlobalNavHost
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private lateinit var mainNavController: NavHostController
    private lateinit var modalNavController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainNavController = rememberNavController()
            val bottomSheetNavigator = rememberCustomBottomSheetNavigator()
            modalNavController = rememberNavController(bottomSheetNavigator)

            ULessonTheme {
                CompositionLocalProvider(
                    LocalNavController provides mainNavController,
                    LocalModalNavController provides modalNavController,
                    LocalFeatureNavigator provides FeatureNavigator(mainNavController),
                    LocalModalFeatureNavigator provides FeatureModalNavigator(modalNavController, mainNavController),
                    LocalParentActivity provides this,
                    LocalIntentNavigator provides IntentNavigator(this)
                ) {
                    GlobalNavHost(bottomSheetNavigator = bottomSheetNavigator)
                }
            }
        }
    }

}