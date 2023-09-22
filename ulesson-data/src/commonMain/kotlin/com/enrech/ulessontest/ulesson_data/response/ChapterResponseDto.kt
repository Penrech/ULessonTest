package com.enrech.ulessontest.ulesson_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChapterResponseDto(
    @SerialName("id") val id: String? = null,
    @SerialName("subjectId") val subjectId: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("order") val order: Int? = null,
    @SerialName("lessonGroups") val lessonGroups: List<LessonGroupResponseDto>? = null,
    @SerialName("totalLessons") val totalLessons: Long? = null
)
