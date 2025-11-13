package uz.shoxrux.feature_media_details.domain.models.movies.details

data class MovieDetailsUi(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDate: String,
    val runtimeMinutes: String?,
    val genres: List<GenreUi>,
    val productionCountries: List<CountryUi>,
    val spokenLanguage: String,
    val rating: String,
    val voteCount: Int,
    val tagline: String?,
    val isRealised: Boolean
)
