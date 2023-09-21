package com.enrech.ulessontest.main.screens

import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel<MainScreenState, MainAction, MainEffect>() {

    override fun createInitialScreenState(): MainScreenState = MainScreenState

    override suspend fun handleActions(action: MainAction) {}
}
object MainScreenState: ScreenState
sealed class MainAction: Action
sealed class MainEffect: Effect