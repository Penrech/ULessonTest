package com.enrech.ulessontest.classes.screens

import com.enrech.ulessontest.common.viewmodel.Action
import com.enrech.ulessontest.common.viewmodel.BaseViewModel
import com.enrech.ulessontest.common.viewmodel.Effect
import com.enrech.ulessontest.common.viewmodel.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassesViewModel @Inject constructor() : BaseViewModel<ClassesScreenState, ClassesAction, ClassesEffect>() {

    override fun createInitialScreenState(): ClassesScreenState = ClassesScreenState

    override suspend fun handleActions(action: ClassesAction) {}
}

object ClassesScreenState : ScreenState
sealed class ClassesAction : Action
sealed class ClassesEffect : Effect