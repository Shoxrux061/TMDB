package uz.isystem.domain.models.movie.movie_detail.crew_details

data class PeopleDetailResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)