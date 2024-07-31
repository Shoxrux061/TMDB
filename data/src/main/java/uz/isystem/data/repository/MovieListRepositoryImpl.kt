package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.MovieService
import uz.isystem.domain.data.MovieListResponse
import uz.isystem.domain.repository.MovieListRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(private val service: MovieService) :
    MovieListRepository<MovieListResponse?> {


    override suspend fun getTopRatedList(
        lang: String,
        page: Int
    ): ResultWrapper<MovieListResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getTopRated(lang, page)
        }
    }
}