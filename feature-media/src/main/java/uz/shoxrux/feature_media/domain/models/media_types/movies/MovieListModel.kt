package uz.shoxrux.feature_media.domain.models.media_types.movies


import com.squareup.moshi.Json

data class MovieListModel(
    @field: Json(name = "dates")
    val dates: MovieDatesModel,
    @field: Json(name = "page")
    val page: Int,
    @field: Json(name = "results")
    val results: List<MovieResultModel>,
    @field: Json(name = "total_pages")
    val totalPages: Int,
    @field: Json(name = "total_results")
    val totalResults: Int
)