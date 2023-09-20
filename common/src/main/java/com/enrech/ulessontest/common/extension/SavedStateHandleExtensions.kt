package com.enrech.ulessontest.common.extension

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.getOrNull(key: String) = runCatching {
    get<T>(key)
}.getOrNull()

fun <T> SavedStateHandle.getOrThrow(key: String) = run {
    requireNotNull(get<T>(key))
}

fun <T> SavedStateHandle.getOrElse(key: String, default: T) = runCatching {
    get<T>(key)
}.getOrNull() ?: default
