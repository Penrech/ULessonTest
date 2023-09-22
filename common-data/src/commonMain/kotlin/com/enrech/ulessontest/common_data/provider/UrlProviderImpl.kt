package com.enrech.ulessontest.common_data.provider

import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.provider.UrlProvider

class UrlProviderImpl @Inject constructor(): UrlProvider {

    private companion object {
        const val DB_URL = "http://127.0.0.1"
    }

    override fun getDbUrl(): String = DB_URL
}