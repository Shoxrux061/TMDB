package uz.shoxrux.feature_media.presentation.main.movies.state

import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieModel

data class MoviesBundle(
    val trending: List<MovieModel>? = null,
    val popular: List<MovieModel>? = null,
    val topRated: List<MovieModel>? = null,
    val upcoming: List<MovieModel>? = null
)
