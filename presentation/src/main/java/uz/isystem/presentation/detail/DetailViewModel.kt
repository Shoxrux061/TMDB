package uz.isystem.presentation.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.movie_detail.DetailResponse
import uz.isystem.domain.models.movie_detail.TrailerResponse
import uz.isystem.domain.models.movie_detail.crew_details.PeopleDetailResponse
import uz.isystem.domain.models.movie_detail.rec.RecommResponse
import uz.isystem.domain.models.movie_detail.similar.SimilarResponse
import uz.isystem.domain.repository.DetailRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository<DetailResponse, TrailerResponse, PeopleDetailResponse?, RecommResponse, SimilarResponse?>,
    @ApplicationContext
    val context: Context
) : ViewModel() {

    private val successDataDetail: MutableLiveData<DetailResponse?> =
        MutableLiveData<DetailResponse?>()
    val successDetail: LiveData<DetailResponse?>
        get() = successDataDetail

    private val errorDataDetail: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorDetail: LiveData<String?>
        get() = errorDataDetail


    fun getMovie(id: Int) {
        viewModelScope.launch {

            when (val result = repository.getMovie(
                id = id,
                apiKey = Constants.API_KEY,
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

    private val successDataTrailer: MutableLiveData<TrailerResponse?> =
        MutableLiveData<TrailerResponse?>()
    val successTrailer: LiveData<TrailerResponse?>
        get() = successDataTrailer

    private val errorDataTrailer: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorTrailer: LiveData<String?>
        get() = errorDataTrailer


    fun getTrailer(id: Int) {
        viewModelScope.launch {

            when (val result = repository.getVideo(
                id = id,
                apiKey = Constants.API_KEY,
                lang = context.getString(R.string.lang)
            )) {

                is ResultWrapper.Success -> {
                    successDataTrailer.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataTrailer.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataTrailer.postValue(result.message.toString())
                }
            }
        }
    }

    private val successDataPeople: MutableLiveData<PeopleDetailResponse?> =
        MutableLiveData<PeopleDetailResponse?>()
    val successPeople: LiveData<PeopleDetailResponse?>
        get() = successDataPeople

    private val errorDataPeople: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorPeople: LiveData<String?>
        get() = errorDataPeople

    fun getMovieCast(id: Int) {
        viewModelScope.launch {

            when (val result = repository.getMovieCrew(
                id = id,
                apiKey = Constants.API_KEY,
                lang = context.getString(R.string.lang)
            )) {

                is ResultWrapper.Success -> {
                    successDataPeople.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataPeople.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataPeople.postValue(result.message.toString())
                }
            }

        }
    }


    private val successDataSimilar: MutableLiveData<SimilarResponse?> =
        MutableLiveData<SimilarResponse?>()
    val successSimilar: LiveData<SimilarResponse?>
        get() = successDataSimilar

    private val errorDataSimilar: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorSimilar: LiveData<String?>
        get() = errorDataSimilar

    fun getSimilar(id: Int) {

        viewModelScope.launch {

            when (val result = repository.getSimilar(
                id = id,
                apiKey = Constants.API_KEY,
                lang = context.getString(R.string.lang)
            )) {

                is ResultWrapper.Success -> {
                    successDataSimilar.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataSimilar.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataSimilar.postValue(result.message.toString())
                }
            }

        }

    }

    private val successDataRecomm: MutableLiveData<RecommResponse?> =
        MutableLiveData<RecommResponse?>()
    val successRecomm: LiveData<RecommResponse?>
        get() = successDataRecomm

    private val errorDataRecomm: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorRecomm: LiveData<String?>
        get() = errorDataRecomm


    fun getRecomm(id: Int){

        viewModelScope.launch {

            when (val result = repository.getRecomm(
                id = id,
                apiKey = Constants.API_KEY,
                lang = context.getString(R.string.lang)
            )) {

                is ResultWrapper.Success -> {
                    successDataRecomm.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    errorDataRecomm.postValue(result.message.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorDataRecomm.postValue(result.message.toString())
                }
            }

        }

    }

}
