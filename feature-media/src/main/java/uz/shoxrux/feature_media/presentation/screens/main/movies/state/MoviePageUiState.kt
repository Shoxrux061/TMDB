package uz.shoxrux.feature_media.presentation.screens.main.movies.state

data class MoviePageUiState(
    val isLoading: Boolean = false,
    val movies: MoviesBundle = MoviesBundle(),
    val error: String? = null
)
