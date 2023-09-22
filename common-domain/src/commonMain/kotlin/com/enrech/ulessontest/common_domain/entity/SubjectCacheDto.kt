package com.enrech.ulessontest.common_domain.entity

data class SubjectCacheDto(
    val id: String,
    val title: String,
    val acronym: String,
    val chapters: List<ChapterCacheDto> = emptyList()
)
