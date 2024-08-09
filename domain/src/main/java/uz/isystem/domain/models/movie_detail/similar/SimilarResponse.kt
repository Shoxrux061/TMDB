package uz.isystem.domain.models.movie_detail.similar

data class SimilarResponse(
    val page: Int,
    val results: List<SimilarResult>,
    val total_pages: Int,
    val total_results: Int
)