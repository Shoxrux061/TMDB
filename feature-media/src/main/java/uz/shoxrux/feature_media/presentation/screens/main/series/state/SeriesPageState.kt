package uz.shoxrux.feature_media.presentation.screens.main.series.state

data class SeriesPageState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val series: SeriesBundle = SeriesBundle()
)