package uz.shoxrux.feature_media_details.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media_details.domain.use_case.GetMovieDetailsUseCase
import uz.shoxrux.feature_media_details.presentation.screens.details.state.DetailScreenState

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState: StateFlow<DetailScreenState> = _uiState

    fun getMovieById(id: Int) {

        viewModelScope.launch {

            try {

                _uiState.value = _uiState.value.copy(isLoading = true)

                getMovieByIdUseCase(id).collect { result ->
                    when (result) {

                        is NetworkResult.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = null,
                                movieDetails = result.data
                            )
                        }

                        is NetworkResult.Error -> {
                            _uiState.value = uiState.value.copy(
                                isLoading = false,
                                error = result.error
                            )
                        }
                    }
                }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = AppError.Unknown(e)
                )
            }
        }
    }
}