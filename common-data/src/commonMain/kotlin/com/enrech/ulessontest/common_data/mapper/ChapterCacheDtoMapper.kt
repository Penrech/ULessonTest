package com.enrech.ulessontest.common_data.mapper

import com.chrynan.inject.Inject
import com.enrech.ulessontest.common_domain.BaseReversibleMapper
import com.enrech.ulessontest.common_domain.entity.ChapterCacheDto
import comenrechulessontestcommondomain.sqldelight.ChapterDto

class ChapterCacheDtoMapper @Inject constructor(): BaseReversibleMapper<ChapterDto, ChapterCacheDto>() {
    override fun mapFrom(from: ChapterDto): ChapterCacheDto =
        ChapterCacheDto(
            id = from.id,
            subjectId = from.id,
            title = from.title,
            orderPosition = from.orderPosition
        )

    override fun mapTo(to: ChapterCacheDto): ChapterDto =
        ChapterDto(
            id = to.id,
            subjectId = to.id,
            title = to.title,
            orderPosition = to.orderPosition
        )
}