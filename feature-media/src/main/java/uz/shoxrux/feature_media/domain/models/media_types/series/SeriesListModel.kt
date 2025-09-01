package uz.shoxrux.feature_media.domain.models.media_types.series


import com.squareup.moshi.Json

data class SeriesListModel(
    @field:Json(name = "page")
    val page: Int,
    @field: Json(name = "results")
    val results: List<SeriesResultModel>,
    @field: Json(name = "total_pages")
    val totalPages: Int,
    @field: Json(name = "total_results")
    val totalResults: Int
)