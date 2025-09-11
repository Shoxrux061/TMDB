package uz.shoxrux.feature_media.presentation.main.movies.state

data class MoviePageUiState(
    val isLoading: Boolean = false,
    val movies: MoviesBundle = MoviesBundle(),
    val error: String? = null
)
