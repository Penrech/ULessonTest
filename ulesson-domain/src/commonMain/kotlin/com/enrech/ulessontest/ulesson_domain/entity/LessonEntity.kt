package com.enrech.ulessontest.ulesson_domain.entity

data class LessonEntity(
    val id: String,
    val name: String,
    val description: String,
    val duration: Long,
    val streamUrl: String
)
