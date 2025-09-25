package uz.shoxrux.feature_media.presentation.screens.main.series.state

import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel

data class SeriesBundle(
    val trending: List<SeriesResultModel> = emptyList(),
    val popular: List<SeriesResultModel> = emptyList(),
    val topRated: List<SeriesResultModel> = emptyList(),
    val onTheAir: List<SeriesResultModel> = emptyList(),
    val airingToday: List<SeriesResultModel> = emptyList(),
)