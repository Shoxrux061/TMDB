package uz.shoxrux.feature_media.domain.models.media_types.movies


import com.squareup.moshi.Json

data class MovieDatesModel(
    @field: Json(name = "maximum")
    val maximum: String,
    @field: Json(name = "minimum")
    val minimum: String
)