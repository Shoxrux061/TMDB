package uz.isystem.domain.repository.people.details

import uz.isystem.utills.ResultWrapper

interface PeopleDetailsRepository<T> {

    suspend fun getPeopleDetails(id: Int, key: String, lang: String): ResultWrapper<T?, Any?>

}