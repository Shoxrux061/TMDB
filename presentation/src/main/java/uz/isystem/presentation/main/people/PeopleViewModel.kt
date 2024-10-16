package uz.isystem.presentation.main.people

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.people.people_list.PeopleListResponse
import uz.isystem.domain.repository.people.PeopleListRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: PeopleListRepository<PeopleListResponse?>,
    @ApplicationContext val context: Context
) : ViewModel() {


    private val successData: MutableLiveData<PeopleListResponse?> =
        MutableLiveData<PeopleListResponse?>()
    val success: LiveData<PeopleListResponse?>
        get() = successData

    private val errorData: MutableLiveData<String?> = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = errorData

    fun getPeopleList() {

        viewModelScope.launch {
            when (val result = repository.getPeopleList(
                lang = context.getString(R.string.lang),
                page = 1,
                apiKey = Constants.API_KEY
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