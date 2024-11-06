package uz.isystem.domain.repository.people.details

import uz.isystem.utills.ResultWrapper

interface PeopleDetailsRepository<T, I, M, Tv, Id> {

    suspend fun getPeopleDetails(id: Int, key: String, lang: String): ResultWrapper<T?, Any?>

    suspend fun getPeopleImages(id: Int, key: String): ResultWrapper<I?, Any?>

    suspend fun getMovieCredits(id: Int, key: String, lang: String): ResultWrapper<M?, Any?>

    suspend fun getTvCredits(id: Int, key: String, lang: String): ResultWrapper<Tv?, Any?>

    suspend fun getPeopleLinks(id: Int, key: String, lang: String): ResultWrapper<Id?, Any?>

}