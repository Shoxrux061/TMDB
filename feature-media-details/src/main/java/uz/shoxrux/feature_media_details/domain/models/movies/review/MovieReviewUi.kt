package uz.shoxrux.feature_media_details.domain.models.movies.review

data class MovieReviewUi(
    val author: String,
    val avatarUrl: String?,
    val rating: Int?,
    val content: String,
    val createdAt: String
)