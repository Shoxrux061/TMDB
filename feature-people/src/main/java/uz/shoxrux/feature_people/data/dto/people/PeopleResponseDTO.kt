package uz.shoxrux.feature_people.data.dto.people


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeopleResponseDTO(
    @field:Json(name = "page")
    val page: Int?,
    @field:Json(name = "results")
    val results: List<PeopleResultDto?>?,
    @field:Json(name = "total_pages")
    val totalPages: Int?,
    @field:Json(name = "total_results")
    val totalResults: Int?
)