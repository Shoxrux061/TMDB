package uz.isystem.domain.repository

import uz.isystem.utills.ResultWrapper

interface DetailRepository<T,E> {

    suspend fun getMovie(lang: String, apiKey: String, id: Int): ResultWrapper<T?, Any?>

    suspend fun getVideo(id: Int, apiKey: String, lang: String) : ResultWrapper<E?, Any>

}