package uz.isystem.presentation.detail.people_details

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.domain.models.people.details.credits.movie.MovieCreditsResponse
import uz.isystem.domain.models.people.details.credits.tv.TvCreditsResponse
import uz.isystem.domain.models.people.details.ids.ExternalIdsResponse
import uz.isystem.domain.models.people.details.images.PeopleImagesResponse
import uz.isystem.domain.repository.people.details.PeopleDetailsRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(
    private val repository: PeopleDetailsRepository<PeopleDetailsResponse?, PeopleImagesResponse?, MovieCreditsResponse?, TvCreditsResponse?, ExternalIdsResponse?>,
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

    private val successDataImages: MutableLiveData<PeopleImagesResponse?> =
        MutableLiveData<PeopleImagesResponse?>()
    val successImages: LiveData<PeopleImagesResponse?>
        get() = successDataImages

    private val errorDataImages: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorImages: LiveData<String?>
        get() = errorDataImages

    fun getPeopleImages(id: Int) {

        viewModelScope.launch {

            when (val result = repository.getPeopleImages(
                id = id,
                key = Constants.API_KEY,
            )) {

                is ResultWrapper.Success -> {
                    successDataImages.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataImages.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataImages.postValue(result.message.toString())
                }
            }
        }
    }


    private val successDataMovie: MutableLiveData<MovieCreditsResponse?> =
        MutableLiveData<MovieCreditsResponse?>()
    val successMovie: LiveData<MovieCreditsResponse?>
        get() = successDataMovie

    private val errorDataMovie: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorMovie: LiveData<String?>
        get() = errorDataMovie

    fun getPeopleMovie(id: Int) {

        viewModelScope.launch {

            when (val result = repository.getMovieCredits(
                id = id,
                key = Constants.API_KEY,
                lang = context.getString(R.string.lang)
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

    private val successDataTv: MutableLiveData<TvCreditsResponse?> =
        MutableLiveData<TvCreditsResponse?>()
    val successTv: LiveData<TvCreditsResponse?>
        get() = successDataTv

    private val errorDataTv: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorTv: LiveData<String?>
        get() = errorDataTv

    fun getPeopleTv(id: Int) {
        viewModelScope.launch {

            when (val result = repository.getTvCredits(
                id = id,
                key = Constants.API_KEY,
                lang = context.getString(R.string.lang)
            )) {

                is ResultWrapper.Success -> {
                    successDataTv.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataTv.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataTv.postValue(result.message.toString())
                }
            }
        }
    }

    private val successDataId: MutableLiveData<ExternalIdsResponse?> =
        MutableLiveData<ExternalIdsResponse?>()
    val successId: LiveData<ExternalIdsResponse?>
        get() = successDataId

    fun getPeopleIds(id: Int) {

        viewModelScope.launch {

            when (val result = repository.getPeopleLinks(
                id = id,
                key = Constants.API_KEY,
                lang = context.getString(R.string.lang)
            )) {

                is ResultWrapper.Success -> {
                    successDataId.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    Log.d("IdError", "getPeopleIds: $result")
                }

                is ResultWrapper.NetworkError -> {
                    Log.d("IdError", "getPeopleIds: $result")
                }
            }
        }
    }
}