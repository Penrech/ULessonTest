package com.enrech.ulessontest.ulesson_domain.entity

data class LessonGroupEntity(
    val id: String,
    val title: String,
    val lessons: List<LessonEntity>
) {
    val lessonQuantity = lessons.count()
}
