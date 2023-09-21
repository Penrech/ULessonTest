package com.enrech.ulessontest.ulesson_domain.entity

data class ChapterEntity(
    val id: String,
    val title: String,
    val order: Int,
    val lessonGroups: List<LessonGroupEntity>
) {
    val totalLessons = lessonGroups.sumOf { it.lessonQuantity }
}