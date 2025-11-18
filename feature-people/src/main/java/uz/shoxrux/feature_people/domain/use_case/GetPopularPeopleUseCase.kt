package uz.shoxrux.feature_people.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_people.domain.model.PeopleUi
import uz.shoxrux.feature_people.domain.repository.PeopleListRepository
import javax.inject.Inject

class GetPopularPeopleUseCase @Inject constructor(
    private val repository: PeopleListRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<PeopleUi>>> = repository.getPeopleList()

}