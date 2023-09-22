package com.enrech.ulessontest.ulesson_data.mapper

import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.Inject
import com.enrech.ulessontest.common_domain.entity.LessonGroupCacheDto
import com.enrech.ulessontest.ulesson_data.response.LessonGroupResponseDto

class LessonGroupResponseCacheMapper @Inject constructor(
    private val lessonResponseCacheMapper: LessonResponseCacheMapper
) : BaseReversibleMapper<LessonGroupResponseDto, LessonGroupCacheDto>() {
    override fun mapFrom(from: LessonGroupResponseDto): LessonGroupCacheDto =
        LessonGroupCacheDto(
            id = from.id ?: throw IllegalArgumentException("id must not be null"),
            chapterId = from.chapterId ?: throw IllegalArgumentException("chapterId must not be null"),
            title = from.title.orEmpty(),
            lessons = lessonResponseCacheMapper.mapFromList(from.lessons)
        )

    override fun mapTo(to: LessonGroupCacheDto): LessonGroupResponseDto =
        LessonGroupResponseDto(
            id = to.id,
            chapterId = to.chapterId,
            title = to.title,
            lessons = lessonResponseCacheMapper.mapToList(to.lessons)
        )
}