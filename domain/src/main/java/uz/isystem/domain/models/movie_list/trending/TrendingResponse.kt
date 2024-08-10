package uz.isystem.domain.models.movie_list.trending

data class TrendingResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)