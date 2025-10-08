package uz.shoxrux.feature_media_details.data.dto.movies.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDatesDTO(
    @field: Json(name = "maximum")
    val maximum: String?,
    @field: Json(name = "minimum")
    val minimum: String?
)