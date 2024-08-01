package uz.isystem.domain.repository

import uz.isystem.utills.ResultWrapper

interface MovieListRepository<T> {

    suspend fun getTopRatedList(lang: String, page: Int, apiKey:String): ResultWrapper<T?, Any?>

}