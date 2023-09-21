package com.enrech.ulessontest.ulesson_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonResponseDto(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("duration") val duration: Long? = null,
    @SerialName("streamUrl") val streamUrl: String? = null
)
