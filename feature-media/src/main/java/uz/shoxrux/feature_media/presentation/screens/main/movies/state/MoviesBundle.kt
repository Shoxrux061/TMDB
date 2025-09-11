package uz.shoxrux.feature_media.presentation.screens.main.movies.state

import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieModel

data class MoviesBundle(
    val trending: List<MovieModel> = emptyList(),
    val popular: List<MovieModel> = emptyList(),
    val topRated: List<MovieModel> = emptyList(),
    val upcoming: List<MovieModel> = emptyList(),
    val nowPlaying: List<MovieModel> = emptyList()
)
