package uz.isystem.domain.repository.search

import uz.isystem.utills.ResultWrapper

interface SearchRepository<M, S, P> {

    suspend fun searchMovie(key: String, lang: String, q: String): ResultWrapper<M?, Any?>

    suspend fun searchSerial(key: String, lang: String, q: String): ResultWrapper<S?, Any?>

    suspend fun searchPerson(key: String, lang: String, q: String): ResultWrapper<P?, Any?>
}