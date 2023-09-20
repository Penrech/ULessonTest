package com.enrech.ulessontest.more.screens

import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(): BaseViewModel<MoreScreenState, MoreAction, MoreEffect>() {

    override fun createInitialScreenState(): MoreScreenState = MoreScreenState

    override suspend fun handleActions(action: MoreAction) {}
}
object MoreScreenState: ScreenState
sealed class MoreAction: Action
sealed class MoreEffect: Effect