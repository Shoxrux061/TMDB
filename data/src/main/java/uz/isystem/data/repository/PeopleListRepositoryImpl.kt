package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.PeopleService
import uz.isystem.domain.models.people.people_list.PeopleListResponse
import uz.isystem.domain.repository.people.PeopleListRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class PeopleListRepositoryImpl @Inject constructor(val service: PeopleService) :
    PeopleListRepository<PeopleListResponse?> {
    override suspend fun getPeopleList(
        page: Int,
        apiKey: String,
        lang: String
    ): ResultWrapper<PeopleListResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPersonList(page = page, lang = lang, apiKey = apiKey)
        }
    }
}