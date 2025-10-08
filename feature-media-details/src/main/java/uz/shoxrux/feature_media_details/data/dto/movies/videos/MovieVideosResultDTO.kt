package uz.shoxrux.feature_media_details.data.dto.movies.videos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieVideosResultDTO(
    @field:Json(name = "id")
    val id: String?,
    @field: Json(name = "iso_3166_1")
    val iso31661: String?,
    @field: Json(name = "iso_639_1")
    val iso6391: String?,
    @field: Json(name = "key")
    val key: String?,
    @field: Json(name = "name")
    val name: String?,
    @field: Json(name = "official")
    val official: Boolean?,
    @field: Json(name = "published_at")
    val publishedAt: String?,
    @field: Json(name = "site")
    val site: String?,
    @field: Json(name = "size")
    val size: Int?,
    @field: Json(name = "type")
    val type: String?
)