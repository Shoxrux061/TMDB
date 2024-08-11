package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.domain.models.people.people_list.PeopleListResponse

interface PeopleService {

    @GET("3/person/popular")
    suspend fun getPersonList(
        @Query("page") page: Int,
        @Query("language") lang: String,
        @Query("api_key") apiKey: String
    ): Response<PeopleListResponse?>

}