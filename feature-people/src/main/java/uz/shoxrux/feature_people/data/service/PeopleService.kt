package uz.shoxrux.feature_people.data.service

import retrofit2.Response
import retrofit2.http.GET
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_people.data.dto.people.PeopleResponseDTO

interface PeopleService {

    @GET("person/popular")
    suspend fun getPeopleList(): Response<PeopleResponseDTO?>

}