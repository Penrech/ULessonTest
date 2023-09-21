package com.enrech.ulessontest.ulesson_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubjectResponseDto(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val title: String? = null,
    @SerialName("acronym") val acronym: String? = null,
    @SerialName("chapters") val chapters: List<ChapterResponseDto>? = null,
    @SerialName("totalChapters") val totalChapters: Long? = null,
    @SerialName("totalLessons") val totalLessons: Long? = null
)
