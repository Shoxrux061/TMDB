package uz.isystem.presentation.main.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import uz.isystem.domain.data.MovieListResponse
import uz.isystem.domain.repository.MovieListRepository
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieListRepository<MovieListResponse>,
    @ApplicationContext context: Context
) : ViewModel() {


    private val successData: MutableLiveData<MovieListResponse?> =
        MutableLiveData<MovieListResponse?>()
    val successResponse: LiveData<MovieListResponse?>
        get() = successData

    private val errorData: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorResponse: LiveData<String?>
        get() = errorData

    fun getTopRatedList(lang: String) {

        viewModelScope.launch {
            when (val result = repository.getTopRatedList(
                lang = lang,
                page = 1,

                )) {
                is ResultWrapper.Success -> {
                    successData.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorData.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorData.postValue("No internet")
                }
            }
        }

    }

}