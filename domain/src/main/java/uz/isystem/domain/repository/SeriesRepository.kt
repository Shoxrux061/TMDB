package uz.isystem.domain.repository

import uz.isystem.utills.ResultWrapper

interface SeriesRepository<T> {

    suspend fun getAiringToday(lang: String, apiKey: String, page: Int): ResultWrapper<T?, Any?>

}