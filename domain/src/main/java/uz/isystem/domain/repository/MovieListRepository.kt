package uz.isystem.domain.repository

import uz.isystem.utills.ResultWrapper

interface MovieListRepository<T> {

    suspend fun getTopRatedList(lang: String, page: Int, apiKey: String): ResultWrapper<T?, Any?>

    suspend fun getNowPlayingList(lang: String, page: Int, apiKey: String): ResultWrapper<T?, Any?>

    suspend fun getPopularList(lang: String, page: Int, apiKey: String): ResultWrapper<T?, Any?>

    suspend fun getUpcomingList(lang: String, page: Int, apiKey: String): ResultWrapper<T?, Any?>

}