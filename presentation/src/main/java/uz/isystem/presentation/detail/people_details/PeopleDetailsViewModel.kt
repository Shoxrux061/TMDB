package uz.isystem.presentation.detail.people_details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.domain.repository.people.details.PeopleDetailsRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(
    private val repository: PeopleDetailsRepository<PeopleDetailsResponse?>,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val successDataDetail: MutableLiveData<PeopleDetailsResponse?> =
        MutableLiveData<PeopleDetailsResponse?>()
    val successDetail: LiveData<PeopleDetailsResponse?>
        get() = successDataDetail

    private val errorDataDetail: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorDetail: LiveData<String?>
        get() = errorDataDetail

    fun getPeopleDetails(id: Int) {

        viewModelScope.launch {

            when (val result = repository.getPeopleDetails(
                id = id,
                key = Constants.API_KEY,
                lang = context.getString(R.string.lang)
            )) {

                is ResultWrapper.Success -> {
                    successDataDetail.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataDetail.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataDetail.postValue(result.message.toString())
                }
            }

        }

    }

}