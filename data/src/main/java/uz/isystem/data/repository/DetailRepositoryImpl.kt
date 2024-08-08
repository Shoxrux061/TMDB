package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.DetailService
import uz.isystem.domain.models.movie_detail.DetailResponse
import uz.isystem.domain.models.movie_detail.TrailerResponse
import uz.isystem.domain.repository.DetailRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val service: DetailService) :
    DetailRepository<DetailResponse?, TrailerResponse?> {
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
}