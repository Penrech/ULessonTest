package com.enrech.ulessontest.common_domain.extension

import android.os.Bundle
import com.enrech.ulessontest.common_domain.entity.ModalFeatureInputData

inline fun <reified T: Any> Bundle.getModalData() = ModalFeatureInputData.parseFromNavigation<T>(
    this.getString(ModalFeatureInputData.MODAL_INPUT_DATA)
)
