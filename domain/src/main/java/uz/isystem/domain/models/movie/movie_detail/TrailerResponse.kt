package uz.isystem.domain.models.movie.movie_detail

data class TrailerResponse(
    val id: Int,
    val results: List<Result>
)