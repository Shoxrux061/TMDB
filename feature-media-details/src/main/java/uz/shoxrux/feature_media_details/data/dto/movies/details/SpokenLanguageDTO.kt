package uz.shoxrux.feature_media_details.data.dto.movies.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguageDTO(
    @field: Json(name = "english_name")
    val englishName: String?,
    @field: Json(name = "iso_639_1")
    val iso6391: String?,
    @field: Json(name = "name")
    val name: String?
)