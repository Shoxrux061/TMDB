package uz.isystem.domain.repository.search

import uz.isystem.utills.ResultWrapper

interface SearchRepository<M> {

    suspend fun searchMovie(key: String, lang: String, q: String): ResultWrapper<M?, Any?>


}