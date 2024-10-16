package uz.isystem.domain.repository.series

import uz.isystem.utills.ResultWrapper

interface SeriesRepository<T> {

    suspend fun getAiringToday(lang: String, apiKey: String, page: Int): ResultWrapper<T?, Any?>

    suspend fun getTrending(lang: String, apiKey: String, page: Int): ResultWrapper<T?, Any?>

    suspend fun getPopular(lang: String, apiKey: String, page: Int): ResultWrapper<T?, Any?>

    suspend fun getTopRated(lang: String, apiKey: String, page: Int): ResultWrapper<T?, Any?>

    suspend fun getOnTheAir(lang: String, apiKey: String, page: Int): ResultWrapper<T?, Any?>

}