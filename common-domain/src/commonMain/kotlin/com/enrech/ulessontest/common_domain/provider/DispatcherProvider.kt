package com.enrech.ulessontest.common_domain.provider

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
}
