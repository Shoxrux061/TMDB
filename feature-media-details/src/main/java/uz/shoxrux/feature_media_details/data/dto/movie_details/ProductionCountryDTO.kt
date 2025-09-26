package uz.shoxrux.feature_media_details.data.dto.movie_details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountryDTO(
    @field: Json(name = "iso_3166_1")
    val iso31661: String?,
    @field: Json(name = "name")
    val name: String?
)