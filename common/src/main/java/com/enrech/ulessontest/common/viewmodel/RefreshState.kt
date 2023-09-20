package com.enrech.ulessontest.common.viewmodel

data class RefreshState(
    var forceRefresh: Boolean = true,
    var isAutoRefreshing: Boolean = false
)