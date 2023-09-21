package com.enrech.ulessontest.common_domain.repository

import com.enrech.ulessontest.common_domain.entity.ChapterCacheDto
import com.enrech.ulessontest.common_domain.entity.LessonCacheDto
import com.enrech.ulessontest.common_domain.entity.LessonGroupCacheDto
import com.enrech.ulessontest.common_domain.entity.SubjectCacheDto

interface CacheDbRepository {

    //Lesson
    fun addLessons(lessons: List<LessonCacheDto>): List<LessonCacheDto>
    fun getLessons(): List<LessonCacheDto>
    fun getLesson(id: String): LessonCacheDto

    //Lesson Group
    fun addLessonGroups(groups: List<LessonGroupCacheDto>): List<LessonGroupCacheDto>
    fun getLessonGroups(): List<LessonGroupCacheDto>
    fun getLessonGroup(id: String): LessonGroupCacheDto

    //Chapter
    fun addChapters(chapters: List<ChapterCacheDto>): List<ChapterCacheDto>
    fun getChapters(): List<ChapterCacheDto>
    fun getChapter(id: String): ChapterCacheDto

    //Subject
    fun addSubjects(subjects: List<SubjectCacheDto>): List<SubjectCacheDto>
    fun getSubjects(): List<SubjectCacheDto>
    fun getSubject(id: String): SubjectCacheDto
}