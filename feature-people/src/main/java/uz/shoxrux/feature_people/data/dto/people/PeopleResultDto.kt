package uz.shoxrux.feature_people.data.dto.people


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeopleResultDto(
    @field:Json(name = "adult")
    val adult: Boolean?,
    @field:Json(name = "gender")
    val gender: Int?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "known_for")
    val knownFor: List<KnownForDto?>?,
    @field:Json(name = "known_for_department")
    val knownForDepartment: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "original_name")
    val originalName: String?,
    @field:Json(name = "popularity")
    val popularity: Double?,
    @field:Json(name = "profile_path")
    val profilePath: String?
)