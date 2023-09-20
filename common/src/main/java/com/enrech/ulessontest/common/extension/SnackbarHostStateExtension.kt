package com.enrech.ulessontest.common.extension

import com.enrech.ulessontest.common.components.snackbar.SnackBar

suspend fun androidx.compose.material.SnackbarHostState.showSnackBar(data: SnackBar) {
    this.showSnackbar(data.message, data.actionLabel, data.duration)
}