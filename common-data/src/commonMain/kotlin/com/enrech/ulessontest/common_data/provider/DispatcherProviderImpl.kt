package com.enrech.ulessontest.common_data.provider

import com.chrynan.inject.Inject
import com.enrech.ulessontest.common_domain.provider.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Main
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun computation(): CoroutineDispatcher = Dispatchers.Default
}
