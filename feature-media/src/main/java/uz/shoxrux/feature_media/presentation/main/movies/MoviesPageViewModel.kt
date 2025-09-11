package uz.shoxrux.feature_media.presentation.main.movies

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media.domain.use_case.movies.GetAllMoviesUseCase
import uz.shoxrux.feature_media.presentation.main.movies.state.MoviePageUiState
import javax.inject.Inject

@HiltViewModel
class MoviesPageViewModel @Inject constructor(
    private val moviesUseCase: GetAllMoviesUseCase
) : ViewModel() {

    val uiState = mutableStateOf(MoviePageUiState())

    fun getMovies() {

        uiState.value = uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            moviesUseCase(page = 1).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        uiState.value = uiState.value.copy(
                            movies = result.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is NetworkResult.Error -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = result.error.toString()
                        )
                    }
                }
            }
        }
    }
}