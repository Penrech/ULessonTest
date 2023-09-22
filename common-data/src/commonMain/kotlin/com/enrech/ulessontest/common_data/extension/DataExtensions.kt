package com.enrech.ulessontest.common_data.extension

import co.touchlab.kermit.Logger
import com.enrech.ulessontest.common_data.isOffline
import com.enrech.ulessontest.common_domain.ULessonError
import com.enrech.ulessontest.common_domain.ULessonResult
import io.ktor.client.network.sockets.SocketTimeoutException

suspend fun <T> safeCall(call: suspend () -> T): ULessonResult<T> {
    return try {
        val result = call()
        ULessonResult.Success(result)
    } catch (throwable: Throwable) {
        Logger.e(throwable) { "HttpClient Error" }
        if (isOffline(throwable)) {
            ULessonResult.Failure(ULessonError.Offline)
        } else {
            when (throwable) {
                is SocketTimeoutException -> ULessonResult.Failure(ULessonError.Timeout)
                else -> ULessonResult.Failure(ULessonError.Unknown)
            }
        }
    }
}

suspend fun <T> safeTransaction(previousError: ULessonError? = null, call: suspend () -> T): ULessonResult<T> {
    return try {
        val result = call()
        ULessonResult.Success(result, previousError)
    } catch (e: Exception) {
        Logger.e(e) { "Db transaction error" }
        ULessonResult.Failure(ULessonError.Unknown)
    }
}