package com.enrech.ulessontest.ulesson_domain.usecase

import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.ulesson_domain.repository.ContentRepository

class LessonGroupUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {

    suspend fun getLessonGroupByChapter(id: String) = contentRepository.getLessonGroups(id)

    suspend fun getLessonGroup(id: String) = contentRepository.getLessonGroup(id)
}