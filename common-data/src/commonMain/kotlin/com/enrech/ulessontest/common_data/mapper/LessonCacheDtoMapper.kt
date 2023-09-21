package com.enrech.ulessontest.common_data.mapper

import com.chrynan.inject.Inject
import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.entity.LessonCacheDto
import comenrechulessontestcommondomain.sqldelight.LessonDto

class LessonCacheDtoMapper @Inject constructor(): BaseReversibleMapper<LessonDto, LessonCacheDto>() {
    override fun mapFrom(from: LessonDto): LessonCacheDto =
        LessonCacheDto(
            id = from.id,
            groupId = from.groupId,
            name = from.name,
            description = from.description,
            duration = from.duration,
            streamUrl = from.streamUrl
        )

    override fun mapTo(to: LessonCacheDto): LessonDto =
        LessonDto(
            id = to.id,
            groupId = to.groupId,
            name = to.name,
            description = to.description,
            duration = to.duration,
            streamUrl = to.streamUrl
        )
}