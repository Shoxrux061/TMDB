package uz.shoxrux.feature_media_details.data.dto.movies.credits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCreditsResponseDTO(
    @field: Json(name = "cast")
    val cast: List<CastDTO?>?,
    @field: Json(name = "crew")
    val crew: List<CrewDTO?>?,
    @field:Json(name = "id")
    val id: Int?
)