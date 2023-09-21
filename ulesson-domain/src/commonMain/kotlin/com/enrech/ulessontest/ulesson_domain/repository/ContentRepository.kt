package com.enrech.ulessontest.ulesson_domain.repository

import com.enrech.ulessontest.common_domain.ULessonResult
import com.enrech.ulessontest.ulesson_domain.entity.ChapterEntity
import com.enrech.ulessontest.ulesson_domain.entity.LessonEntity
import com.enrech.ulessontest.ulesson_domain.entity.LessonGroupEntity
import com.enrech.ulessontest.ulesson_domain.entity.SubjectEntity

interface ContentRepository {
    suspend fun getSubjects(): ULessonResult<List<SubjectEntity>>
    suspend fun getSubject(id: String): ULessonResult<SubjectEntity>
    suspend fun getChapters(subjectId: String): ULessonResult<List<ChapterEntity>>
    suspend fun getChapter(id: String): ULessonResult<ChapterEntity>
    suspend fun getLessonGroups(chapterId: String): ULessonResult<List<LessonGroupEntity>>
    suspend fun getLessonGroup(id: String): ULessonResult<LessonGroupEntity>
    suspend fun getLessons(groupId: String): ULessonResult<List<LessonEntity>>
    suspend fun getLesson(id: String): ULessonResult<LessonEntity>
}