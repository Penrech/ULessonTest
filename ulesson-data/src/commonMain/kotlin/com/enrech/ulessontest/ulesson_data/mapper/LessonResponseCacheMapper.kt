package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.LessonCacheDto
import com.enrech.ulessontest.ulesson_data.response.LessonResponseDto

class LessonResponseCacheMapper @Inject constructor(): BaseReversibleMapper<LessonResponseDto, LessonCacheDto>() {
    override fun mapFrom(from: LessonResponseDto): LessonCacheDto =
        LessonCacheDto(
            id = from.id ?: throw IllegalArgumentException("id must not be null"),
            groupId = from.groupId ?: throw IllegalArgumentException("groupId must not be null"),
            name = from.name.orEmpty(),
            description = from.description.orEmpty(),
            duration = from.duration ?: 0L,
            streamUrl = from.streamUrl.orEmpty()
        )

    override fun mapTo(to: LessonCacheDto): LessonResponseDto =
        LessonResponseDto(
            id = to.id,
            groupId = to.id,
            name = to.name,
            description = to.description,
            duration = to.duration,
            streamUrl = to.streamUrl
        )
}