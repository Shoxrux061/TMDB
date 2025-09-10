package uz.shoxrux.feature_media.data.dto.movie


import com.squareup.moshi.Json

data class MovieListDTO(
    @field: Json(name = "dates")
    val dates: MovieDatesDTO?,
    @field: Json(name = "page")
    val page: Int?,
    @field: Json(name = "results")
    val results: List<MovieResultDTO?>?,
    @field: Json(name = "total_pages")
    val totalPages: Int?,
    @field: Json(name = "total_results")
    val totalResults: Int?
)