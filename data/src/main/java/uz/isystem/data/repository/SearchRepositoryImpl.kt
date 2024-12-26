package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.SearchService
import uz.isystem.domain.models.movie.movie_list.MovieListResponse
import uz.isystem.domain.repository.search.SearchRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(val service: SearchService) :
    SearchRepository<MovieListResponse?> {
    override suspend fun searchMovie(
        key: String,
        lang: String,
        q: String
    ): ResultWrapper<MovieListResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.searchMovie(q = q, lang = lang, key = key)
        }
    }

}