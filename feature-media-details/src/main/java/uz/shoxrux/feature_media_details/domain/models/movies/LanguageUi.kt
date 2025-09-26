package uz.shoxrux.feature_media_details.domain.models.movies

data class LanguageUi(
    val code: String,   // iso_639_1 → "en"
    val englishName: String,
    val localName: String?
)