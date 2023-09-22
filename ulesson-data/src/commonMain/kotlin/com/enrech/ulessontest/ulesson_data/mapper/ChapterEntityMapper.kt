package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.ChapterCacheDto
import com.enrech.ulessontest.ulesson_domain.entity.ChapterEntity

class ChapterEntityMapper @Inject constructor(
    private val lessonGroupEntityMapper: LessonGroupEntityMapper
): BaseMapper<ChapterCacheDto, ChapterEntity>() {
    override fun mapFrom(from: ChapterCacheDto): ChapterEntity =
        ChapterEntity(
            id = from.id,
            title = from.title,
            order = from.orderPosition,
            lessonGroups = lessonGroupEntityMapper.mapFromList(from.lessonGroups)
        )
}