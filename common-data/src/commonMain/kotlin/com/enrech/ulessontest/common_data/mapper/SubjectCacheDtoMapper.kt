package com.enrech.ulessontest.common_data.mapper

import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.SubjectCacheDto
import comenrechulessontestcommondomain.sqldelight.SubjectDto

class SubjectCacheDtoMapper @Inject constructor(): BaseReversibleMapper<SubjectDto, SubjectCacheDto>() {
    override fun mapFrom(from: SubjectDto): SubjectCacheDto =
        SubjectCacheDto(
            id = from.id,
            title = from.title,
            acronym = from.acronym
        )

    override fun mapTo(to: SubjectCacheDto): SubjectDto =
        SubjectDto(
            id = to.id,
            title = to.title,
            acronym = to.acronym
        )
}