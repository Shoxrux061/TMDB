package uz.shoxrux.feature_media_details.data.dto.movies.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewAuthorDetailsDTO(
    @field: Json(name = "avatar_path")
    val avatarPath: String?,
    @field: Json(name = "name")
    val name: String?,
    @field: Json(name = "rating")
    val rating: Int?,
    @field: Json(name = "username")
    val username: String?
)