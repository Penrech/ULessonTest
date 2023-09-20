package com.enrech.ulessontest.common.extension

import android.content.Intent
import com.enrech.ulessontest.common_domain.entity.ModalDestination
import com.enrech.ulessontest.common_domain.entity.ModalResult

inline fun <reified T : ModalDestination<*, *>> Intent.putModalDestination(victim: T): Intent =
    putExtra(T::class.java.name, victim)

inline fun <reified T: ModalDestination<*, *>> Intent.getModalDestination(): T? =
    getParcelableExtra(T::class.java.name)

inline fun <reified T: ModalResult> Intent.putModalResult(victim: T): Intent =
    putExtra(T::class.java.name, victim)

inline fun <reified T: ModalResult> Intent.getModalResult(): T? = getParcelableExtra(T::class.java.name)