package com.enrech.ulessontest.screens

import androidx.lifecycle.viewModelScope
import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import com.enrech.ulessontest.common_domain.provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<SplashScreenState, SplashAction, SplashEffect>() {

    override fun createInitialScreenState(): SplashScreenState = SplashScreenState

    init {
       flow<Unit> { delay(300) }
           .flowOn(dispatcherProvider.io())
           .onEach { setEffect { SplashEffect.LaunchMainActivity } }
           .launchIn(viewModelScope)
    }

    override suspend fun handleActions(action: SplashAction) {}
}

object SplashScreenState : ScreenState
sealed class SplashAction : Action
sealed class SplashEffect : Effect {
    data object LaunchMainActivity: SplashEffect()
}