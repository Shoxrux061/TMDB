package uz.shoxrux.feature_media_details.data.dto.movies.review


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieReviewsResponseDTO(
    @field: Json(name = "id")
    val id: Int?,
    @field: Json(name = "page")
    val page: Int?,
    @field: Json(name = "results")
    val results: List<ReviewResultDTO?>?,
    @field: Json(name = "total_pages")
    val totalPages: Int?,
    @field: Json(name = "total_results")
    val totalResults: Int?
)