package uz.shoxrux.feature_media_details.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.utils.onResult
import uz.shoxrux.feature_media_details.domain.use_case.movie.CombineMovieScreenUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieDetailsUseCase
import uz.shoxrux.feature_media_details.presentation.screens.details.state.DetailScreenState

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val combineMovieScreenUseCase: CombineMovieScreenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState: StateFlow<DetailScreenState> = _uiState

    fun getMovieById(id: Int) {

        viewModelScope.launch {

            try {

                _uiState.value = _uiState.value.copy(isLoading = true)

                combineMovieScreenUseCase.invoke(id).onResult(
                    onSuccess = {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            movieDetails = it.detailsUi,
                            movieCredits = it.creditsUi,
                            similarMovies = it.similarMovies,
                            recommendedMovies = it.recommendedMovies,
                            reviews = it.reviews,
                            contentUi = it.content
                        )
                    },
                    onError = {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = it
                        )
                    }
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = AppError.Unknown(e)
                )
            }
        }
    }
}