package com.enrech.ulessontest.common_data.repository

import com.enrech.ulessontest.common_data.mapper.ChapterCacheDtoMapper
import com.enrech.ulessontest.common_data.mapper.LessonCacheDtoMapper
import com.enrech.ulessontest.common_data.mapper.LessonGroupCacheDtoMapper
import com.enrech.ulessontest.common_data.mapper.SubjectCacheDtoMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.ULessonCacheDb
import com.enrech.ulessontest.common_domain.entity.ChapterCacheDto
import com.enrech.ulessontest.common_domain.entity.LessonCacheDto
import com.enrech.ulessontest.common_domain.entity.LessonGroupCacheDto
import com.enrech.ulessontest.common_domain.entity.SubjectCacheDto
import com.enrech.ulessontest.common_domain.repository.CacheDbRepository

class CacheDbRepositoryImpl @Inject constructor(
    private val db: ULessonCacheDb,
    private val lessonCacheDtoMapper: LessonCacheDtoMapper,
    private val lessonGroupCacheDtoMapper: LessonGroupCacheDtoMapper,
    private val chapterCacheDtoMapper: ChapterCacheDtoMapper,
    private val subjectCacheDtoMapper: SubjectCacheDtoMapper
) : CacheDbRepository {
    override fun addLessons(lessons: List<LessonCacheDto>): List<LessonCacheDto> =
        db.transactionWithResult { addLessonsQuery(lessons) }

    override fun addLesson(lesson: LessonCacheDto): LessonCacheDto = db.transactionWithResult {
        db.lessonQueries.insertLesson(lessonCacheDtoMapper.mapTo(lesson))
        lessonCacheDtoMapper.mapFrom(db.lessonQueries.getLesson(lesson.id).executeAsOne())
    }

    private fun addLessonsQuery(lessons: List<LessonCacheDto>): List<LessonCacheDto> {
        lessonCacheDtoMapper.mapToList(lessons).forEach {
            db.lessonQueries.insertLesson(it)
        }
        val lessonsIds = lessons.map { it.id }
        return lessonCacheDtoMapper.mapFromList(db.lessonQueries.getLessonsByIds(lessonsIds).executeAsList())
    }

    override fun getLessons(): List<LessonCacheDto> =
        lessonCacheDtoMapper.mapFromList(db.lessonQueries.getAllLesson().executeAsList())

    override fun getLessonsByGroup(id: String): List<LessonCacheDto> =
        lessonCacheDtoMapper.mapFromList(db.lessonQueries.getLessonsByGroupId(listOf(id)).executeAsList())

    override fun getLesson(id: String): LessonCacheDto =
        lessonCacheDtoMapper.mapFrom(db.lessonQueries.getLesson(id).executeAsOne())

    override fun addLessonGroups(groups: List<LessonGroupCacheDto>): List<LessonGroupCacheDto> =
        db.transactionWithResult {
            addLessonGroupsQuery(groups)
        }

    override fun addLessonGroup(group: LessonGroupCacheDto): LessonGroupCacheDto = db.transactionWithResult {
        db.lessonGroupQueries.insertLessonGroup(lessonGroupCacheDtoMapper.mapTo(group))
        lessonGroupCacheDtoMapper.mapFrom(db.lessonGroupQueries.getLessonGroup(group.id).executeAsOne())
    }

    private fun addLessonGroupsQuery(groups: List<LessonGroupCacheDto>): List<LessonGroupCacheDto> {
        val lessons = groups.flatMap { it.lessons }

        lessonGroupCacheDtoMapper.mapToList(groups).forEach {
            db.lessonGroupQueries.insertLessonGroup(it)
        }

        addLessonsQuery(lessons)

        return getLessonGroupsQuery()
    }

    override fun getLessonGroups(): List<LessonGroupCacheDto> =
        db.transactionWithResult { getLessonGroupsQuery() }

    override fun getLessonGroupsByChapter(id: String): List<LessonGroupCacheDto> =
        lessonGroupCacheDtoMapper.mapFromList(db.lessonGroupQueries.getLessonGroupSByChapterId(listOf(id)).executeAsList())

    private fun getLessonGroupsQuery(chapterIds: List<String> = emptyList()): List<LessonGroupCacheDto> {
        val rawGroups =
            if (chapterIds.isEmpty()) {
                db.lessonGroupQueries.getAllLessonGroup().executeAsList()
            } else {
                db.lessonGroupQueries.getLessonGroupSByChapterId(chapterIds).executeAsList()
            }
        val groupsIds = rawGroups.map { it.id }
        val allLessons = lessonCacheDtoMapper.mapFromList(
            db.lessonQueries.getLessonsByGroupId(groupsIds).executeAsList()
        )

        return lessonGroupCacheDtoMapper.mapFromList(rawGroups).map { group ->
            group.copy(lessons = allLessons.filter { it.groupId == group.id })
        }
    }

    override fun getLessonGroup(id: String): LessonGroupCacheDto =
        lessonGroupCacheDtoMapper.mapFrom(db.lessonGroupQueries.getLessonGroup(id).executeAsOne())

    override fun addChapters(chapters: List<ChapterCacheDto>): List<ChapterCacheDto> =
        db.transactionWithResult { addChaptersQuery(chapters) }

    override fun addChapter(chapter: ChapterCacheDto): ChapterCacheDto = db.transactionWithResult {
        db.chapterQueries.insertChapter(chapterCacheDtoMapper.mapTo(chapter))
        chapterCacheDtoMapper.mapFrom(db.chapterQueries.getChapter(chapter.id).executeAsOne())
    }

    private fun addChaptersQuery(chapters: List<ChapterCacheDto>): List<ChapterCacheDto> {
        val lessonGroups = chapters.flatMap { it.lessonGroups }

        chapterCacheDtoMapper.mapToList(chapters).forEach {
            db.chapterQueries.insertChapter(it)
        }

        addLessonGroupsQuery(lessonGroups)

        return getChaptersQuery()
    }

    override fun getChapters(): List<ChapterCacheDto> = db.transactionWithResult { getChaptersQuery() }

    override fun getChaptersBySubject(id: String): List<ChapterCacheDto> =
        chapterCacheDtoMapper.mapFromList(db.chapterQueries.getChaptersBySubjectId(listOf(id)).executeAsList())

    private fun getChaptersQuery(subjectsIds: List<String> = emptyList()): List<ChapterCacheDto> {
        val chapters = if (subjectsIds.isEmpty()) {
            db.chapterQueries.getAllChapter().executeAsList()
        } else {
            db.chapterQueries.getChaptersBySubjectId(subjectsIds).executeAsList()
        }
        val chaptersIds = chapters.map { it.id }
        val groups = getLessonGroupsQuery(chaptersIds)

        return chapterCacheDtoMapper.mapFromList(chapters).map {  chapter ->
            chapter.copy(lessonGroups = groups.filter { it.chapterId == chapter.id })
        }
    }

    override fun getChapter(id: String): ChapterCacheDto =
        chapterCacheDtoMapper.mapFrom(db.chapterQueries.getChapter(id).executeAsOne())

    override fun replaceSubjects(subjects: List<SubjectCacheDto>): List<SubjectCacheDto> = db.transactionWithResult {
        db.subjectQueries.clearSubject()
        subjectCacheDtoMapper.mapToList(subjects).forEach {
            db.subjectQueries.insertSubject(it)
        }
        val chapters = subjects.flatMap { it.chapters }

        addChaptersQuery(chapters)

        getSubjectsQuery()
    }

    override fun addSubject(subject: SubjectCacheDto): SubjectCacheDto = db.transactionWithResult {
        db.subjectQueries.insertSubject(subjectCacheDtoMapper.mapTo(subject))
        subjectCacheDtoMapper.mapFrom(db.subjectQueries.getSubject(subject.id).executeAsOne())
    }

    override fun getSubjects(): List<SubjectCacheDto> = db.transactionWithResult { getSubjectsQuery() }

    private fun getSubjectsQuery(): List<SubjectCacheDto> {
        val subjects = db.subjectQueries.getAllSubject().executeAsList()
        val subjectsIds = subjects.map { it.id }
        val chapters = getChaptersQuery(subjectsIds)

        return subjectCacheDtoMapper.mapFromList(subjects).map { subject ->
            subject.copy(chapters = chapters.filter { it.subjectId == subject.id })
        }
    }

    override fun getSubject(id: String): SubjectCacheDto =
        subjectCacheDtoMapper.mapFrom(db.subjectQueries.getSubject(id).executeAsOne())
}