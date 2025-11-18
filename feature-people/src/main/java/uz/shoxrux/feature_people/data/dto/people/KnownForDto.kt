package uz.shoxrux.feature_people.data.dto.people


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KnownForDto(
    @field:Json(name = "adult")
    val adult: Boolean?,
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
    @field:Json(name = "first_air_date")
    val firstAirDate: String?,
    @field:Json(name = "genre_ids")
    val genreIds: List<Int?>?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "media_type")
    val mediaType: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "origin_country")
    val originCountry: List<String?>?,
    @field:Json(name = "original_language")
    val originalLanguage: String?,
    @field:Json(name = "original_name")
    val originalName: String?,
    @field:Json(name = "original_title")
    val originalTitle: String?,
    @field:Json(name = "overview")
    val overview: String?,
    @field:Json(name = "popularity")
    val popularity: Double?,
    @field:Json(name = "poster_path")
    val posterPath: String?,
    @field:Json(name = "release_date")
    val releaseDate: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "video")
    val video: Boolean?,
    @field:Json(name = "vote_average")
    val voteAverage: Double?,
    @field:Json(name = "vote_count")
    val voteCount: Int?
)