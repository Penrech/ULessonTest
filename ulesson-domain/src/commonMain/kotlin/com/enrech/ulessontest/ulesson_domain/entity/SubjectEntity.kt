package com.enrech.ulessontest.ulesson_domain.entity

data class SubjectEntity(
    val id: String,
    val name: String,
    val acronym: String,
    val chapters: List<ChapterEntity>
) {
    val totalChapters = chapters.count()
    val totalLessons = chapters.sumOf { it.totalLessons }
}
