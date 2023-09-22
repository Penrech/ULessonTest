package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.LessonCacheDto
import com.enrech.ulessontest.ulesson_domain.entity.LessonEntity

class LessonEntityMapper @Inject constructor(): BaseMapper<LessonCacheDto, LessonEntity>() {
    override fun mapFrom(from: LessonCacheDto): LessonEntity =
        LessonEntity(
            id = from.id,
            name = from.name,
            description = from.description,
            duration = from.duration,
            streamUrl = from.streamUrl
        )
}