package uz.isystem.presentation.main.serials

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.tv_series_list.SeriesResponse
import uz.isystem.domain.repository.SeriesRepository
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
}