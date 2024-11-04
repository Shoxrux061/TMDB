package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.PeopleDetailService
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.domain.repository.people.details.PeopleDetailsRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class PeopleDetailsRepositoryImpl @Inject constructor(private val service: PeopleDetailService) :
    PeopleDetailsRepository<PeopleDetailsResponse?> {


    override suspend fun getPeopleDetails(
        id: Int,
        key: String,
        lang: String
    ): ResultWrapper<PeopleDetailsResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPeopleDetails(id = id, lang = lang, key = key)
        }
    }

}