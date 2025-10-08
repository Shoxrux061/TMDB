package uz.shoxrux.feature_media_details.data.dto.movies.images


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDTO(
    @field: Json(name = "aspect_ratio")
    val aspectRatio: Double?,
    @field: Json(name = "file_path")
    val filePath: String?,
    @field: Json(name = "height")
    val height: Int?,
    @field: Json(name = "iso_639_1")
    val iso6391: String?,
    @field: Json(name = "vote_average")
    val voteAverage: Double?,
    @field: Json(name = "vote_count")
    val voteCount: Int?,
    @field: Json(name = "width")
    val width: Int?
)