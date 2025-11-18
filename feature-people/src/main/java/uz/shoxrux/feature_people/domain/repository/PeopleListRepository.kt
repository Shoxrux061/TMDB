package uz.shoxrux.feature_people.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_people.domain.model.PeopleUi

interface PeopleListRepository {

    fun getPeopleList(): Flow<NetworkResult<List<PeopleUi>>>

}