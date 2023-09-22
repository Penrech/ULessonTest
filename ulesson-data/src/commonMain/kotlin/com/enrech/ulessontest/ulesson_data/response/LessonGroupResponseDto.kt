package com.enrech.ulessontest.ulesson_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonGroupResponseDto(
    @SerialName("id") val id: String? = null,
    @SerialName("chapterId") val chapterId: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("lessonsQuantity") val quantity: Long? = null,
    @SerialName("lessons") val lessons: List<LessonResponseDto>? = null
)
