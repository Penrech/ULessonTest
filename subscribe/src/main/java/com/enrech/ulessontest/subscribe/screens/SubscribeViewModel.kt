package com.enrech.ulessontest.subscribe.screens

import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubscribeViewModel @Inject constructor(): BaseViewModel<SubscribeScreenState, SubscribeAction, SubscribeEffect>() {

    override fun createInitialScreenState(): SubscribeScreenState = SubscribeScreenState

    override suspend fun handleActions(action: SubscribeAction) {}
}
object SubscribeScreenState: ScreenState
sealed class SubscribeAction: Action
sealed class SubscribeEffect: Effect