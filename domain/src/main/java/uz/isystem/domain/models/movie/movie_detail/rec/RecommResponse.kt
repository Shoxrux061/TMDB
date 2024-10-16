package uz.isystem.domain.models.movie.movie_detail.rec

data class RecommResponse(
    val page: Int,
    val results: List<RecommResult>,
    val total_pages: Int,
    val total_results: Int
)