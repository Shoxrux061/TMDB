package uz.shoxrux.feature_media_details.domain.models.movies

data class MovieDetailsUi(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDate: String,
    val runtimeMinutes: Int?,
    val genres: List<GenreUi>,
    val productionCountries: List<CountryUi>,
    val spokenLanguages: List<LanguageUi>,
    val rating: Double,
    val voteCount: Int,
    val tagline: String?
)
