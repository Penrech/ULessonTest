package com.enrech.ulessontest.ulesson_domain.usecase

import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.ulesson_domain.repository.ContentRepository

class LessonUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {

    suspend fun getLessonsByGroup(id: String) = contentRepository.getLessons(id)

    suspend fun getLesson(id: String) = contentRepository.getLesson(id)
}