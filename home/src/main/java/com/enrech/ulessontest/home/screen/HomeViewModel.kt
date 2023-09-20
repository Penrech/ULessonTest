package com.enrech.ulessontest.home.screen

import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeScreenState, HomeAction, HomeEffect>() {

    override fun createInitialScreenState(): HomeScreenState = HomeScreenState

    override suspend fun handleActions(action: HomeAction) {}
}

object HomeScreenState : ScreenState
sealed class HomeAction : Action
sealed class HomeEffect : Effect