package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.SeriesService
import uz.isystem.domain.models.tv_series_list.SeriesResponse
import uz.isystem.domain.repository.SeriesRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(val service: SeriesService): SeriesRepository<SeriesResponse?> {
    override suspend fun getAiringToday(lang:String, apiKey:String, page:Int): ResultWrapper<SeriesResponse?, Any?> {
        return parseResponse(Dispatchers.IO){
            service.getAiringToday(lang = lang, apiKey = apiKey, page = page)
        }
    }
}