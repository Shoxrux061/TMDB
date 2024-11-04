package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isystem.domain.models.people.details.PeopleDetailsResponse

interface PeopleDetailService {

    @GET("3/person/{id}")
    suspend fun getPeopleDetails(
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Response<PeopleDetailsResponse?>

}