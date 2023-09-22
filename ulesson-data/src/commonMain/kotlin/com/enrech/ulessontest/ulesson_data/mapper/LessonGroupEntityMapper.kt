package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.LessonGroupCacheDto
import com.enrech.ulessontest.ulesson_domain.entity.LessonGroupEntity

class LessonGroupEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper
): BaseMapper<LessonGroupCacheDto, LessonGroupEntity>() {
    override fun mapFrom(from: LessonGroupCacheDto): LessonGroupEntity =
        LessonGroupEntity(
            id = from.id,
            title = from.title,
            lessons = lessonEntityMapper.mapFromList(from.lessons)
        )
}