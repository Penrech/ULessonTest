package com.enrech.ulessontest.ulesson_domain.usecase

import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.ulesson_domain.repository.ContentRepository


class ChapterUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun getChaptersBySubject(id: String) = contentRepository.getChapters(id)

    suspend fun getChapter(id: String) = contentRepository.getChapter(id)
}