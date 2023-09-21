package com.enrech.ulessontest.common_domain

sealed class ULessonResult<out T> {
    data class Success<out T>(val data: T) : ULessonResult<T>()
    data class Failure<T>(val error: ULessonError) : ULessonResult<T>()
}

fun <T> T.toResult(): ULessonResult<T> {
    return ULessonResult.Success(this)
}

fun <T> ULessonError.toResult(): ULessonResult<T> {
    return ULessonResult.Failure(this)
}

fun <T> ULessonResult<T>.isSuccess(): Boolean {
    return this is ULessonResult.Success
}

fun <T> ULessonResult<T>.isFailure(): Boolean {
    return this is ULessonResult.Failure
}

fun <T> ULessonResult<T>.getData(): T {
    return (this as ULessonResult.Success).data
}

fun <T> ULessonResult<T>.getError(): ULessonError {
    return (this as ULessonResult.Failure).error
}

fun <T> ULessonResult<T>.getOrElse(onFailure: (error: ULessonError) -> T) = when (this) {
    is ULessonResult.Success -> data
    is ULessonResult.Failure -> onFailure(error)
}

fun <T> ULessonResult<T>.getOrNull() = when (this) {
    is ULessonResult.Success -> data
    is ULessonResult.Failure -> null
}

fun <T> ULessonResult<T>.getErrorOrNull() = when(this) {
    is ULessonResult.Success -> null
    is ULessonResult.Failure -> error
}

inline fun <R, T> ULessonResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (error: ULessonError) -> R
): R = when (this) {
    is ULessonResult.Success -> onSuccess(data)
    is ULessonResult.Failure -> onFailure(error)
}

inline fun <R, T> ULessonResult<T>.map(
    onSuccess: (value: T) -> R,
    onFailure: (error: ULessonError) -> ULessonError = { error -> error }
): ULessonResult<R> = when (this) {
    is ULessonResult.Success -> ULessonResult.Success(onSuccess(data))
    is ULessonResult.Failure -> ULessonResult.Failure(onFailure(error))
}

inline fun <R, T> ULessonResult<T>.map(
    transform: (T) -> R
): ULessonResult<R> = when (this) {
    is ULessonResult.Success -> ULessonResult.Success(transform(data))
    is ULessonResult.Failure -> ULessonResult.Failure(error)
}

inline fun <R, T> ULessonResult<T>.flatMap(
    transform: (T) -> ULessonResult<R>
): ULessonResult<R> = when (this) {
    is ULessonResult.Success -> transform(data)
    is ULessonResult.Failure -> ULessonResult.Failure(error)
}

inline fun <T> ULessonResult<T>.onSuccess(
    onSuccess: (value: T) -> Unit
): ULessonResult<T> {
    if (this is ULessonResult.Success) onSuccess(data)
    return this
}

inline fun <T> ULessonResult<T>.onFailure(
    onFailure: (error: ULessonError) -> Unit
): ULessonResult<T> {
    if (this is ULessonResult.Failure) onFailure(error)
    return this
}
