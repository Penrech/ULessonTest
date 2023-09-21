package com.enrech.ulessontest.common_domain.entity

data class LessonCacheDto(
    val id: String,
    val groupId: String,
    val name: String,
    val description: String,
    val duration: Long,
    val streamUrl: String
)
