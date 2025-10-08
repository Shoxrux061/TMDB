package uz.shoxrux.feature_media_details.data.dto.movies.images


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieImagesResponseDTO(
    @field: Json(name = "backdrops")
    val backdrops: List<ImageDTO?>?,
    @field: Json(name = "id")
    val id: Int?,
    @field: Json(name = "logos")
    val logos: List<ImageDTO?>?,
    @field: Json(name = "posters")
    val posters: List<ImageDTO?>?
)