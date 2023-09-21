package com.enrech.ulessontest.common_data.provider

import com.chrynan.inject.Inject
import com.enrech.ulessontest.common_domain.provider.UrlProvider

class UrlProviderImpl @Inject constructor(): UrlProvider {

    private companion object {
        const val DB_URL = ""
    }

    override fun getDbUrl(): String {
        TODO("Not yet implemented")
    }
}