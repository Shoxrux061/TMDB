package uz.isystem.domain.models.series.tv_series_list

data class SeriesResponse(
    val page: Int,
    val results: List<SerialsResult>,
    val total_pages: Int,
    val total_results: Int,
    var sortType : Int
)