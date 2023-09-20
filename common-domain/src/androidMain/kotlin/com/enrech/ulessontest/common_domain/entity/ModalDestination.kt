package com.enrech.ulessontest.common_domain.entity

import android.content.Intent
import android.os.Parcelable

interface ModalDestination<T: ModalData, R: ModalResult>: Parcelable {
    val data: T

    fun getResultIntent(result: R): Intent = Intent()
}

