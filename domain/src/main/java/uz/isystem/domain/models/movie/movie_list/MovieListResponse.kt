package uz.isystem.domain.models.movie.movie_list

data class MovieListResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int,
    var sortType : Int
)