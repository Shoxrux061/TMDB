package uz.isystem.presentation.main.serials

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse
import uz.isystem.domain.repository.series.SeriesRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repository: SeriesRepository<SeriesResponse?>,
    @ApplicationContext val context: Context
) :
    ViewModel() {

    private val successDataToday: MutableLiveData<SeriesResponse?> =
        MutableLiveData<SeriesResponse?>()
    val successToday: LiveData<SeriesResponse?>
        get() = successDataToday

    private val errorDataToday: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorToday: LiveData<String?>
        get() = errorDataToday


    fun getAiringToday() {

        viewModelScope.launch {
            when (val result = repository.getAiringToday(
                lang = context.getString(R.string.lang),
                page = 1,
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataToday.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataToday.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataToday.postValue("No internet")
                }
            }
        }
    }

    private val successDataTrending: MutableLiveData<SeriesResponse?> =
        MutableLiveData<SeriesResponse?>()
    val successTrending: LiveData<SeriesResponse?>
        get() = successDataTrending

    private val errorDataTrending: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorTrending: LiveData<String?>
        get() = errorDataTrending


    fun getTrending() {

        viewModelScope.launch {
            when (val result = repository.getTrending(
                lang = context.getString(R.string.lang),
                page = 1,
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

    private val successDataPopular: MutableLiveData<SeriesResponse?> =
        MutableLiveData<SeriesResponse?>()
    val successPopular: LiveData<SeriesResponse?>
        get() = successDataPopular

    private val errorDataPopular: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorPopular: LiveData<String?>
        get() = errorDataPopular


    fun getPopular() {

        viewModelScope.launch {
            when (val result = repository.getPopular(
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

    private val successDataTopRated: MutableLiveData<SeriesResponse?> =
        MutableLiveData<SeriesResponse?>()
    val successTopRated: LiveData<SeriesResponse?>
        get() = successDataTopRated

    private val errorDataTopRated: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorTopRated: LiveData<String?>
        get() = errorDataTopRated

    fun getTopRatedList() {

        viewModelScope.launch {
            when (val result = repository.getTopRated(
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

    private val successDataOnTheAir: MutableLiveData<SeriesResponse?> =
        MutableLiveData<SeriesResponse?>()
    val successOnTheAir: LiveData<SeriesResponse?>
        get() = successDataOnTheAir

    private val errorDataOnTheAir: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorOnTheAir: LiveData<String?>
        get() = errorDataOnTheAir

    fun getOnTheAir() {

        viewModelScope.launch {
            when (val result = repository.getOnTheAir(
                lang = context.getString(R.string.lang),
                page = 1,
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataOnTheAir.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataOnTheAir.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataOnTheAir.postValue("No internet")
                }
            }
        }

    }



}