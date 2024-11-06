package uz.isystem.domain.models.people.details.credits.tv

data class TvCreditsResponse(
    val cast: List<Cast>?,
    val crew: List<Any>?,
    val id: Int?
)