package com.enrech.ulessontest.common_data

import io.ktor.client.engine.darwin.DarwinHttpRequestException

actual fun isOffline(throwable: Throwable): Boolean = throwable is DarwinHttpRequestException
