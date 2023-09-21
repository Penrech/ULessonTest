package com.enrech.ulessontest.common_data.mapper

import com.chrynan.inject.Inject
import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.entity.LessonGroupCacheDto
import comenrechulessontestcommondomain.sqldelight.LessonGroupDto

class LessonGroupCacheDtoMapper @Inject constructor(): BaseReversibleMapper<LessonGroupDto, LessonGroupCacheDto>() {
    override fun mapFrom(from: LessonGroupDto): LessonGroupCacheDto =
        LessonGroupCacheDto(
            id = from.id,
            chapterId = from.chapterId,
            title = from.title
        )

    override fun mapTo(to: LessonGroupCacheDto): LessonGroupDto =
        LessonGroupDto(
            id = to.id,
            chapterId = to.chapterId,
            title = to.title
        )
}