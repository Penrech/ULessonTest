package com.enrech.ulessontest.ulesson_data.service

import com.chrynan.inject.Singleton
import com.enrech.ulessontest.common_data.provider.UrlProvider
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.ulesson_data.response.ChapterResponseDto
import com.enrech.ulessontest.ulesson_data.response.LessonGroupResponseDto
import com.enrech.ulessontest.ulesson_data.response.LessonResponseDto
import com.enrech.ulessontest.ulesson_data.response.SubjectResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

@Singleton
class ContentService @Inject constructor(
    private val urlProvider: UrlProvider,
    private val client: HttpClient
) {

    private val baseContentUrl by lazy { urlProvider.getDbUrl().plus(CONTENT) }

    internal suspend fun getAllLesson(): List<LessonResponseDto> =
        client.get(baseContentUrl.plus(LESSONS)).body()

    internal suspend fun getLessonsByGroup(id: String): List<LessonResponseDto> =
        client.get(baseContentUrl.plus(LESSONS)) {
            parameter(GROUP_ID_PARAM, id)
        }.body()

    internal suspend fun getLesson(id: String): LessonResponseDto =
        client.get(baseContentUrl.plus(LESSONS).plus("/$id")).body()

    internal suspend fun getLessonGroupsByChapter(id: String): List<LessonGroupResponseDto> =
        client.get(baseContentUrl.plus(GROUPS)) {
            parameter(CHAPTER_ID_PARAM, id)
        }.body()

    internal suspend fun getAllLessonGroups(): List<LessonGroupResponseDto> =
        client.get(baseContentUrl.plus(GROUPS)).body()

    internal suspend fun getLessonGroup(id: String): LessonGroupResponseDto =
        client.get(baseContentUrl.plus(GROUPS).plus("/$id")).body()

    internal suspend fun getChaptersBySubject(id: String): List<ChapterResponseDto> =
        client.get(baseContentUrl.plus(CHAPTER)){
            parameter(SUBJECT_ID_PARAM, id)
        }.body()

    internal suspend fun getAllChapters(): List<ChapterResponseDto> =
        client.get(baseContentUrl.plus(CHAPTER)).body()

    internal suspend fun getChapter(id: String): ChapterResponseDto =
        client.get(baseContentUrl.plus(CHAPTER).plus("/$id")).body()

    internal suspend fun getSubjectByChapterId(chapterId: String): List<SubjectResponseDto> =
        client.get(baseContentUrl.plus(SUBJECT)){
            parameter(CHAPTER_ID_PARAM, chapterId)
        }.body()
    
    internal suspend fun getAllSubject(): List<SubjectResponseDto> =
        client.get(baseContentUrl.plus(SUBJECT)).body()
    
    internal suspend fun getSubject(id: String): SubjectResponseDto =
        client.get(baseContentUrl.plus(SUBJECT).plus("/$id")).body()

    private companion object {
        const val CONTENT = "/content"
        const val LESSONS = "/lesson"
        const val GROUPS = "/group"
        const val CHAPTER = "/chapter"
        const val SUBJECT = "/subject"

        const val LESSON_ID_PARAM = "lessonId"
        const val GROUP_ID_PARAM = "groupId"
        const val CHAPTER_ID_PARAM = "chapterId"
        const val SUBJECT_ID_PARAM = "subjectId"
    }
}