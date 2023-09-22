package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.SubjectCacheDto
import com.enrech.ulessontest.ulesson_domain.entity.SubjectEntity

class SubjectEntityMapper @Inject constructor(
    private val chapterEntityMapper: ChapterEntityMapper
): BaseMapper<SubjectCacheDto, SubjectEntity>() {
    override fun mapFrom(from: SubjectCacheDto): SubjectEntity =
        SubjectEntity(
            id = from.id,
            name = from.title,
            acronym = from.acronym,
            chapters = chapterEntityMapper.mapFromList(from.chapters)
        )
}