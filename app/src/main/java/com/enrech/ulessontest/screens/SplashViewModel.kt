package com.enrech.ulessontest.screens

import androidx.lifecycle.viewModelScope
import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import com.enrech.ulessontest.common_domain.provider.DispatcherProvider
import com.enrech.ulessontest.ulesson_domain.usecase.ChapterUseCase
import com.enrech.ulessontest.ulesson_domain.usecase.LessonGroupUseCase
import com.enrech.ulessontest.ulesson_domain.usecase.LessonUseCase
import com.enrech.ulessontest.ulesson_domain.usecase.SubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    subjectUseCase: SubjectUseCase,
    chapterUseCase: ChapterUseCase,
    lessonGroupUseCase: LessonGroupUseCase,
    lessonUseCase: LessonUseCase
) : BaseViewModel<SplashScreenState, SplashAction, SplashEffect>() {

    override fun createInitialScreenState(): SplashScreenState = SplashScreenState

    init {
       flow { emit(delay(300)) }
           .mapLatest {
               val subjects = subjectUseCase.getSubjects()
               val dummy = ""
           }
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