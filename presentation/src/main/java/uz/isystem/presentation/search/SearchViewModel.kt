package uz.isystem.presentation.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.movie.movie_list.MovieListResponse
import uz.isystem.domain.repository.search.SearchRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository<MovieListResponse?>,
    @ApplicationContext
    private val context: Context
) : ViewModel() {

    private val successDataMovie: MutableLiveData<MovieListResponse?> =
        MutableLiveData<MovieListResponse?>()
    val successMovie: LiveData<MovieListResponse?>
        get() = successDataMovie

    private val errorDataMovie: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorMovie: LiveData<String?>
        get() = errorDataMovie

    fun searchMovie(q: String) {

        viewModelScope.launch {

            when (val result = repository.searchMovie(
                q = q,
                lang = context.getString(R.string.lang),
                key = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataMovie.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataMovie.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataMovie.postValue(result.message.toString())
                }
            }
        }
    }
}