package com.enrech.ulessontest.ulesson_data.service

import com.chrynan.inject.Inject
import com.chrynan.inject.Singleton
import com.enrech.ulessontest.common_data.provider.UrlProvider
import io.ktor.client.HttpClient

@Singleton
class ContentService @Inject constructor(
    private val urlProvider: UrlProvider,
    private val httpClient: HttpClient
) {
    internal suspend fun
}