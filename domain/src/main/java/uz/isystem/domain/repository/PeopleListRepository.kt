package uz.isystem.domain.repository

import uz.isystem.utills.ResultWrapper

interface PeopleListRepository<T> {
    suspend fun getPeopleList(page:Int, apiKey : String, lang:String) : ResultWrapper<T?, Any?>
}