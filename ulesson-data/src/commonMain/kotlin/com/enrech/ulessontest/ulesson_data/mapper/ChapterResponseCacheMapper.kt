package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.ChapterCacheDto
import com.enrech.ulessontest.ulesson_data.response.ChapterResponseDto

class ChapterResponseCacheMapper @Inject constructor(
    private val lessonGroupResponseCacheMapper: LessonGroupResponseCacheMapper
): BaseReversibleMapper<ChapterResponseDto, ChapterCacheDto>() {
    override fun mapFrom(from: ChapterResponseDto): ChapterCacheDto =
        ChapterCacheDto(
            id = from.id ?: throw IllegalArgumentException("id must not be null"),
            subjectId = from.subjectId ?: throw IllegalArgumentException("SubjectId must not be null"),
            title = from.title.orEmpty(),
            orderPosition = from.order ?: 0,
            lessonGroups = lessonGroupResponseCacheMapper.mapFromList(from.lessonGroups)
        )

    override fun mapTo(to: ChapterCacheDto): ChapterResponseDto =
        ChapterResponseDto(
            id = to.id,
            subjectId = to.subjectId,
            title = to.title,
            order = to.orderPosition,
            lessonGroups = lessonGroupResponseCacheMapper.mapToList(to.lessonGroups)
        )
}