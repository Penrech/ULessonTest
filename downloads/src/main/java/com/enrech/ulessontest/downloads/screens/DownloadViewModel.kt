package com.enrech.ulessontest.downloads.screens

import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(): BaseViewModel<DownloadScreenState, DownloadAction, DownloadEffect>() {

    override fun createInitialScreenState(): DownloadScreenState = DownloadScreenState

    override suspend fun handleActions(action: DownloadAction) {}
}
object DownloadScreenState: ScreenState
sealed class DownloadAction: Action
sealed class DownloadEffect: Effect