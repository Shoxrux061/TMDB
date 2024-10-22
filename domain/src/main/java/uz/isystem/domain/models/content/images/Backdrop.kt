package uz.isystem.domain.models.content.images


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Backdrop(
    @SerialName("aspect_ratio")
    val aspect_ratio: Double?,
    @SerialName("file_path")
    val file_path: String?,
    @SerialName("height")
    val height: Int?,
    @SerialName("iso_639_1")
    val iso_639_1: String?,
    @SerialName("vote_average")
    val vote_average: Double?,
    @SerialName("vote_count")
    val vote_count: Int?,
    @SerialName("width")
    val width: Int?
)