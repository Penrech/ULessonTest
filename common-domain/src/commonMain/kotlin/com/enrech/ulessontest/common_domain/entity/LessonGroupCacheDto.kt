package com.enrech.ulessontest.common_domain.entity

data class LessonGroupCacheDto(
    val id: String,
    val chapterId: String,
    val title: String,
    val lessons: List<LessonCacheDto> = emptyList()
)
