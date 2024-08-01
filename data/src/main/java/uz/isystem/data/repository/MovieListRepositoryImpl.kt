package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.MovieService
import uz.isystem.domain.models.MovieListResponse
import uz.isystem.domain.repository.MovieListRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(private val service: MovieService) :
    MovieListRepository<MovieListResponse?> {


    override suspend fun getTopRatedList(
        lang: String,
        page: Int,
        apiKey:String
    ): ResultWrapper<MovieListResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getTopRated(lang = lang, page = page, apiKey = apiKey)
        }
    }

    override suspend fun getNowPlayingList(
        lang: String,
        page: Int,
        apiKey: String
    ): ResultWrapper<MovieListResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getNowPlaying(lang = lang, page = page, apiKey = apiKey)
        }
    }

    override suspend fun getPopularList(
        lang: String,
        page: Int,
        apiKey: String
    ): ResultWrapper<MovieListResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPopular(lang = lang, page = page, apiKey = apiKey)
        }

    }

    override suspend fun getUpcomingList(
        lang: String,
        page: Int,
        apiKey: String
    ): ResultWrapper<MovieListResponse?, Any?> {
        return parseResponse(Dispatchers.IO){
            service.getUpcoming(lang = lang, page = page, apiKey = apiKey)
        }
    }
}