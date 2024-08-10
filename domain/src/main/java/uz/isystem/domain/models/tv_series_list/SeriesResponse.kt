package uz.isystem.domain.models.tv_series_list

data class SeriesResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int,
    var sortType : Int
)