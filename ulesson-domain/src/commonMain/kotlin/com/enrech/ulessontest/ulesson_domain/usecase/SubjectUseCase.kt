package com.enrech.ulessontest.ulesson_domain.usecase

import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.ulesson_domain.repository.ContentRepository

class SubjectUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun getSubjects() = contentRepository.getSubjects()

    suspend fun getSubject(id: String) = contentRepository.getSubject(id)
}