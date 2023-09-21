package com.enrech.ulessontest.common_data.provider

import com.chrynan.inject.Inject

class UrlProviderImpl @Inject constructor(): UrlProvider {

    private companion object {
        const val DB_URL = ""
    }

    override fun getDbUrl(): String {
        TODO("Not yet implemented")
    }
}