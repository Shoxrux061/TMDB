package uz.shoxrux.feature_media.data.dto.series


import com.squareup.moshi.Json

data class SeriesListDTO(
    @field: Json(name = "page")
    val page: Int,
    @field: Json(name = "results")
    val results: List<SeriesResultDTO>,
    @field: Json(name = "total_pages")
    val totalPages: Int,
    @field: Json(name = "total_results")
    val totalResults: Int
)