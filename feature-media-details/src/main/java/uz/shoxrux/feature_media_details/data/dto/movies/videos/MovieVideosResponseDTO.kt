package uz.shoxrux.feature_media_details.data.dto.movies.videos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieVideosResponseDTO(
    @field: Json(name = "id")
    val id: Int?,
    @field: Json(name = "results")
    val results: List<MovieVideosResultDTO?>?
)