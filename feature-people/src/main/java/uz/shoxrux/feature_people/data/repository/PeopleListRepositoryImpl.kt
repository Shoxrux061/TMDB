package uz.shoxrux.feature_people.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.safeApiCall
import uz.shoxrux.feature_people.data.mapper.toDomain
import uz.shoxrux.feature_people.data.service.PeopleService
import uz.shoxrux.feature_people.domain.model.PeopleUi
import uz.shoxrux.feature_people.domain.repository.PeopleListRepository
import javax.inject.Inject

class PeopleListRepositoryImpl @Inject constructor(
    private val service: PeopleService
) : PeopleListRepository {

    override fun getPeopleList(): Flow<NetworkResult<List<PeopleUi>>> = flow {
        emit(
            safeApiCall(
                apiCall = { service.getPeopleList() },
                mapper = { dto ->
                    dto?.results
                        ?.filterNotNull()
                        ?.map { it.toDomain() }
                        ?: emptyList()
                }
            )
        )
    }
}