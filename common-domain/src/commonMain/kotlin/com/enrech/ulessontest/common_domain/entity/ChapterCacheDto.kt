package com.enrech.ulessontest.common_domain.entity

data class ChapterCacheDto(
    val id: String,
    val subjectId: String,
    val title: String,
    val orderPosition: Int,
    val lessonGroups: List<LessonGroupCacheDto> = emptyList()
)
