package uz.isystem.domain.models.people.details.credits.movie

data class MovieCreditsResponse(
    val cast: List<Cast>?,
    val crew: List<Crew>?,
    val id: Int?
)