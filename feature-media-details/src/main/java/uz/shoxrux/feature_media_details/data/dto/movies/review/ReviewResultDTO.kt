package uz.shoxrux.feature_media_details.data.dto.movies.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResultDTO(
    @field: Json(name = "author")
    val author: String?,
    @field: Json(name = "author_details")
    val authorDetails: ReviewAuthorDetailsDTO?,
    @field: Json(name = "content")
    val content: String?,
    @field: Json(name = "created_at")
    val createdAt: String?,
    @field: Json(name = "id")
    val id: String?,
    @field: Json(name = "updated_at")
    val updatedAt: String?,
    @field: Json(name = "url")
    val url: String?
)