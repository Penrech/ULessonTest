package com.enrech.ulessontest.common.navigation

interface ModalFeatureDestination: Destination {

    val launcher: ModalFeatureLauncher? get() = null

    companion object {
        const val MODAL_BOTTOM_SHEET = "modal_bottom_sheet"
    }
}
