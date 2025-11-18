package uz.shoxrux.feature_people.presentation.screens.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.shoxrux.core.handler.ErrorHandler
import uz.shoxrux.core.utils.onResult
import uz.shoxrux.feature_people.domain.use_case.GetPopularPeopleUseCase
import uz.shoxrux.feature_people.presentation.screens.people.state.PeopleScreenState
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val useCase: GetPopularPeopleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PeopleScreenState())
    val uiState: StateFlow<PeopleScreenState> = _uiState

    fun getPeopleList() {

        viewModelScope.launch {

            try {
                _uiState.update { it.copy(isLoading = true) }

                useCase.invoke().collect { result ->

                    result.onResult(
                        onSuccess = { data ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    peopleList = data,
                                    error = null
                                )
                            }
                        },
                        onError = { error ->
                            _uiState.update { it.copy(isLoading = false, error = error) }
                        }
                    )
                }

            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = ErrorHandler.parse(e)
                    )
                }
            }
        }
    }
}