package uz.shoxrux.feature_media.presentation.screens.main.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media.domain.use_case.series.GetAllSeriesUseCase
import uz.shoxrux.feature_media.presentation.screens.main.series.state.SeriesPageState

@HiltViewModel
class SeriesPageViewModel @Inject constructor(
    private val seriesUseCase: GetAllSeriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SeriesPageState())
    val uiState: StateFlow<SeriesPageState> = _uiState

    fun getSeries() {
        _uiState.value = uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            seriesUseCase(page = 1).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.value = uiState.value.copy(
                            series = result.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is NetworkResult.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = result.error.toString()
                        )
                    }
                }
            }
        }

    }
}