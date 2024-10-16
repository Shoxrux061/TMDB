package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.DetailService
import uz.isystem.domain.models.movie.movie_detail.DetailResponse
import uz.isystem.domain.models.movie.movie_detail.TrailerResponse
import uz.isystem.domain.models.movie.movie_detail.crew_details.PeopleDetailResponse
import uz.isystem.domain.models.movie.movie_detail.rec.RecommResponse
import uz.isystem.domain.models.movie.movie_detail.similar.SimilarResponse
import uz.isystem.domain.models.series.series_details.SeriesDetailResponse
import uz.isystem.domain.repository.movie.DetailRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val service: DetailService) :
    DetailRepository<DetailResponse?, TrailerResponse?, PeopleDetailResponse?, RecommResponse?, SimilarResponse?, SeriesDetailResponse?> {


    override suspend fun getMovie(
        lang: String,
        apiKey: String,
        id: Int
    ): ResultWrapper<DetailResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getMovie(lang = lang, apiKey = apiKey, id = id)
        }
    }

    override suspend fun getVideo(
        id: Int,
        apiKey: String,
        lang: String
    ): ResultWrapper<TrailerResponse?, Any> {
        return parseResponse(Dispatchers.IO) {
            service.getVideos(lang = lang, apiKey = apiKey, id = id)
        }
    }

    override suspend fun getMovieCrew(
        id: Int,
        apiKey: String,
        lang: String
    ): ResultWrapper<PeopleDetailResponse?, Any> {
        return parseResponse(Dispatchers.IO) {
            service.getMovieCrew(id = id, apiKey = apiKey, lang = lang)
        }
    }

    override suspend fun getRecomm(
        lang: String,
        apiKey: String,
        id: Int
    ): ResultWrapper<RecommResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getMovieRecommendations(id = id, apiKey = apiKey, lang = lang)
        }
    }

    override suspend fun getSimilar(
        id: Int,
        apiKey: String,
        lang: String
    ): ResultWrapper<SimilarResponse?, Any> {

        return parseResponse(Dispatchers.IO) {
            service.getMovieSimilar(id = id, apiKey = apiKey, lang = lang)
        }
    }

    override suspend fun getSeriesDetail(
        lang: String,
        apiKey: String,
        id: Int
    ): ResultWrapper<SeriesDetailResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getSerial(id = id, lang = lang, key = apiKey)
        }
    }
}