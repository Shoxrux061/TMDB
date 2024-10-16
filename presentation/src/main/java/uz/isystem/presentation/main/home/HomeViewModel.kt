package uz.isystem.presentation.main.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.movie.movie_list.MovieListResponse
import uz.isystem.domain.repository.movie.MovieListRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieListRepository<MovieListResponse?>,
    @ApplicationContext
    val context: Context
) : ViewModel() {


    private val successDataTopRated: MutableLiveData<MovieListResponse?> =
        MutableLiveData<MovieListResponse?>()
    val successTopRated: LiveData<MovieListResponse?>
        get() = successDataTopRated

    private val errorDataTopRated: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorTopRated: LiveData<String?>
        get() = errorDataTopRated

    fun getTopRatedList() {

        viewModelScope.launch {
            when (val result = repository.getTopRatedList(
                lang = context.getString(R.string.lang),
                page = 1,
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataTopRated.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataTopRated.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataTopRated.postValue("No internet")
                }
            }
        }
    }


    private val successDataNowPlaying: MutableLiveData<MovieListResponse?> =
        MutableLiveData<MovieListResponse?>()
    val successNowPLaying: LiveData<MovieListResponse?>
        get() = successDataNowPlaying

    private val errorDataNowPlaying: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorNowPlaying: LiveData<String?>
        get() = errorDataNowPlaying

    fun getNowPlaying() {

        viewModelScope.launch {
            when (val result = repository.getNowPlayingList(
                lang = context.getString(R.string.lang),
                page = 1,
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataNowPlaying.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataNowPlaying.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataNowPlaying.postValue("No internet")
                }
            }
        }
    }


    private val successDataPopular: MutableLiveData<MovieListResponse?> =
        MutableLiveData<MovieListResponse?>()
    val successPopular: LiveData<MovieListResponse?>
        get() = successDataPopular

    private val errorDataPopular: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorPopular: LiveData<String?>
        get() = errorDataPopular

    fun getPopular() {

        viewModelScope.launch {
            when (val result = repository.getPopularList(
                lang = context.getString(R.string.lang),
                page = 1,
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataPopular.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataPopular.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataPopular.postValue("No internet")
                }
            }
        }
    }


    private val successDataUpcoming: MutableLiveData<MovieListResponse?> =
        MutableLiveData<MovieListResponse?>()
    val successUpcoming: LiveData<MovieListResponse?>
        get() = successDataUpcoming

    private val errorDataUpcoming: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorUpcoming: LiveData<String?>
        get() = errorDataUpcoming

    fun getUpcoming() {

        viewModelScope.launch {
            when (val result = repository.getUpcomingList(
                lang = context.getString(R.string.lang),
                page = 1,
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataUpcoming.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataUpcoming.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataUpcoming.postValue("No internet")
                }
            }
        }
    }

    private val successDataTrending: MutableLiveData<MovieListResponse?> =
        MutableLiveData<MovieListResponse?>()
    val successTrending: LiveData<MovieListResponse?>
        get() = successDataTrending

    private val errorDataTrending: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorTrending: LiveData<String?>
        get() = errorDataTrending


    fun getTrending() {
        viewModelScope.launch {
            when (val result = repository.getTrendingList(
                lang = context.getString(R.string.lang),
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataTrending.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataTrending.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataTrending.postValue("No internet")
                }
            }
        }
    }
}