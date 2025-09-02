package uz.shoxrux.feature_media.domain.models.media_types

enum class MovieType(val category: String) {
    Popular("popular"),
    Trending("trending"),
    TopRated("top_rated"),
    NowPlaying("now_playing"),
    Upcoming("upcoming")
}