package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.SubjectCacheDto
import com.enrech.ulessontest.ulesson_data.response.SubjectResponseDto

class SubjectResponseCacheMapper @Inject constructor(
    private val chapterResponseCacheMapper: ChapterResponseCacheMapper
): BaseReversibleMapper<SubjectResponseDto, SubjectCacheDto>() {
    override fun mapFrom(from: SubjectResponseDto): SubjectCacheDto =
        SubjectCacheDto(
            id = from.id ?: throw IllegalArgumentException("id must not be null"),
            acronym = from.acronym ?: throw IllegalArgumentException("acronym must not be null"),
            title = from.title.orEmpty(),
            chapters = chapterResponseCacheMapper.mapFromList(from.chapters)
        )

    override fun mapTo(to: SubjectCacheDto): SubjectResponseDto =
        SubjectResponseDto(
            id = to.id,
            acronym = to.id,
            title = to.id,
            chapters = chapterResponseCacheMapper.mapToList(to.chapters)
        )
}