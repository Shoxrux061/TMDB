package uz.isystem.presentation.detail.film_details

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import uz.isystem.domain.models.content.images.ImagesData
import uz.isystem.domain.models.movie.movie_detail.DetailResponse
import uz.isystem.domain.models.movie.movie_detail.TrailerResponse
import uz.isystem.domain.models.movie.movie_detail.crew_details.PeopleDetailResponse
import uz.isystem.domain.models.movie.movie_detail.rec.RecommResponse
import uz.isystem.domain.models.movie.movie_detail.similar.SimilarResponse
import uz.isystem.domain.models.series.series_details.SeriesDetailResponse
import uz.isystem.domain.repository.movie.DetailRepository
import uz.isystem.presentation.R
import uz.isystem.utills.Constants
import uz.isystem.utills.ResultWrapper
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository<DetailResponse, TrailerResponse, PeopleDetailResponse?, RecommResponse, SimilarResponse?, SeriesDetailResponse?, ImagesData?>,
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


    fun getRecomm(id: Int) {

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

    private val successDataDetails: MutableLiveData<SeriesDetailResponse?> =
        MutableLiveData<SeriesDetailResponse?>()
    val successDetailsSerial: LiveData<SeriesDetailResponse?>
        get() = successDataDetails

    fun getSerial(id: Int) {

        viewModelScope.launch {
            when (val result = repository.getSeriesDetail(
                lang = context.getString(R.string.lang),
                apiKey = Constants.API_KEY,
                id = id
            )) {
                is ResultWrapper.Success -> {
                    successDataDetails.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    Log.d("serial_status", "getSerial: Error${result.message}")
                }

                is ResultWrapper.NetworkError -> {
                    Log.d("serial_status", "getSerial: Error${result.message}")
                }
            }
        }
    }

    private val successDataImages: MutableLiveData<ImagesData?> =
        MutableLiveData<ImagesData?>()
    val successImages: LiveData<ImagesData?>
        get() = successDataImages


    private val errorDataImage: MutableLiveData<String?> = MutableLiveData<String?>()
    val errorImage: LiveData<String?>
        get() = errorDataImage

    fun getImages(id: Int) {

        viewModelScope.launch {

            when (val result = repository.getImages(
                id = id,
                apiKey = Constants.API_KEY
            )) {
                is ResultWrapper.Success -> {
                    successDataImages.postValue(result.data)
                }

                is ResultWrapper.Error -> {
                    Log.d("serial_status", "getSerial: Error${result.message}")
                }

                is ResultWrapper.NetworkError -> {
                    Log.d("serial_status", "getSerial: Error${result.message}")

                }
            }
        }
    }
}
