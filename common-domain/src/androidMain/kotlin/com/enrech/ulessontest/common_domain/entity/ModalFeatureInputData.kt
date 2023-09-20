package com.enrech.ulessontest.common_domain.entity

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import timber.log.Timber

/**
 Param [strategy] is required to force [T] to be serializable
 **/
class ModalFeatureInputData<T>(
    val data: T,
    private val strategy: SerializationStrategy<T>
) {
    fun getParams(): Map<String, Any> =
        try {
            parseMap(Json.encodeToString(strategy, data))
        } catch (e: Exception) {
            Timber.e(e)
            parseMap("")
        }

    private fun parseMap(value: String): Map<String, String> = mapOf(MODAL_INPUT_DATA to value)

    companion object {
        inline fun <reified T> parseFromNavigation(value: String?): T? =
            value?.let {
                try {
                    Json.decodeFromString<T>(value)
                } catch (e: Exception) {
                    Timber.e(e)
                    null
                }
            }

        const val MODAL_INPUT_DATA = "modal_input_data"
        val init: ModalFeatureInputData<*> = ModalFeatureInputData("", String.serializer())
    }
}
