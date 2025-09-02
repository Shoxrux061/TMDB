package uz.shoxrux.feature_media.domain.models.media_types

enum class SeriesType(
    val category: String
) {
    Popular("popular"),
    Trending("trending"),
    TopRated("top_rated"),
    OnTheAir("on_the_air"),
    AiringToday("airing_today")
}