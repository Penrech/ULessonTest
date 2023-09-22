package com.enrech.ulessontest.ulesson_data.repository

import com.enrech.ulessontest.common_data.extension.safeCall
import com.enrech.ulessontest.common_data.extension.safeTransaction
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.ULessonResult
import com.enrech.ulessontest.common_domain.repository.CacheDbRepository
import com.enrech.ulessontest.common_domain.transform
import com.enrech.ulessontest.ulesson_data.mapper.ChapterEntityMapper
import com.enrech.ulessontest.ulesson_data.mapper.ChapterResponseCacheMapper
import com.enrech.ulessontest.ulesson_data.mapper.LessonEntityMapper
import com.enrech.ulessontest.ulesson_data.mapper.LessonGroupEntityMapper
import com.enrech.ulessontest.ulesson_data.mapper.LessonGroupResponseCacheMapper
import com.enrech.ulessontest.ulesson_data.mapper.LessonResponseCacheMapper
import com.enrech.ulessontest.ulesson_data.mapper.SubjectEntityMapper
import com.enrech.ulessontest.ulesson_data.mapper.SubjectResponseCacheMapper
import com.enrech.ulessontest.ulesson_data.service.ContentService
import com.enrech.ulessontest.ulesson_domain.entity.ChapterEntity
import com.enrech.ulessontest.ulesson_domain.entity.LessonEntity
import com.enrech.ulessontest.ulesson_domain.entity.LessonGroupEntity
import com.enrech.ulessontest.ulesson_domain.entity.SubjectEntity
import com.enrech.ulessontest.ulesson_domain.repository.ContentRepository

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val dbRepository: CacheDbRepository,
    private val lessonResponseCacheMapper: LessonResponseCacheMapper,
    private val lessonGroupResponseCacheMapper: LessonGroupResponseCacheMapper,
    private val chapterResponseCacheMapper: ChapterResponseCacheMapper,
    private val subjectResponseCacheMapper: SubjectResponseCacheMapper,
    private val lessonEntityMapper: LessonEntityMapper,
    private val lessonGroupEntityMapper: LessonGroupEntityMapper,
    private val chapterEntityMapper: ChapterEntityMapper,
    private val subjectEntityMapper: SubjectEntityMapper
): ContentRepository {
    override suspend fun getSubjects(): ULessonResult<List<SubjectEntity>> = safeCall {
         contentService.getAllSubject()
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = subjectResponseCacheMapper.mapFromList(it)
                subjectEntityMapper.mapFromList(dbRepository.replaceSubjects(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                subjectEntityMapper.mapFromList(dbRepository.getSubjects())
            }
        }
    )

    override suspend fun getSubject(id: String): ULessonResult<SubjectEntity> = safeCall {
        contentService.getSubject(id)
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = subjectResponseCacheMapper.mapFrom(it)
                subjectEntityMapper.mapFrom(dbRepository.addSubject(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                subjectEntityMapper.mapFrom(dbRepository.getSubject(id))
            }
        }
    )

    override suspend fun getChapters(subjectId: String): ULessonResult<List<ChapterEntity>> = safeCall {
        contentService.getChaptersBySubject(subjectId)
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = chapterResponseCacheMapper.mapFromList(it)
                chapterEntityMapper.mapFromList(dbRepository.addChapters(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                chapterEntityMapper.mapFromList(dbRepository.getChaptersBySubject(subjectId))
            }
        }
    )

    override suspend fun getChapter(id: String): ULessonResult<ChapterEntity> = safeCall {
        contentService.getChapter(id)
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = chapterResponseCacheMapper.mapFrom(it)
                chapterEntityMapper.mapFrom(dbRepository.addChapter(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                chapterEntityMapper.mapFrom(dbRepository.getChapter(id))
            }
        }
    )

    override suspend fun getLessonGroups(chapterId: String): ULessonResult<List<LessonGroupEntity>> = safeCall {
        contentService.getLessonGroupsByChapter(chapterId)
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = lessonGroupResponseCacheMapper.mapFromList(it)
                lessonGroupEntityMapper.mapFromList(dbRepository.addLessonGroups(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                lessonGroupEntityMapper.mapFromList(dbRepository.getLessonGroupsByChapter(chapterId))
            }
        }
    )

    override suspend fun getLessonGroup(id: String): ULessonResult<LessonGroupEntity> = safeCall {
        contentService.getLessonGroup(id)
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = lessonGroupResponseCacheMapper.mapFrom(it)
                lessonGroupEntityMapper.mapFrom(dbRepository.addLessonGroup(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                lessonGroupEntityMapper.mapFrom(dbRepository.getLessonGroup(id))
            }
        }
    )

    override suspend fun getLessons(groupId: String): ULessonResult<List<LessonEntity>> = safeCall {
        contentService.getLessonsByGroup(groupId)
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = lessonResponseCacheMapper.mapFromList(it)
                lessonEntityMapper.mapFromList(dbRepository.addLessons(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                lessonEntityMapper.mapFromList(dbRepository.getLessonsByGroup(groupId))
            }
        }
    )

    override suspend fun getLesson(id: String): ULessonResult<LessonEntity> = safeCall {
        contentService.getLesson(id)
    }.transform(
        onSuccess = {
            safeTransaction {
                val data = lessonResponseCacheMapper.mapFrom(it)
                lessonEntityMapper.mapFrom(dbRepository.addLesson(data))
            }
        },
        onFailure = {
            safeTransaction(it) {
                lessonEntityMapper.mapFrom(dbRepository.getLesson(id))
            }
        }
    )
}