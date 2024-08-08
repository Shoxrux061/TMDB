package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isystem.domain.models.movie_detail.DetailResponse
import uz.isystem.domain.models.movie_detail.TrailerResponse
import uz.isystem.domain.models.movie_detail.crew_details.PeopleDetailResponse

interface DetailService {

    @GET("3/movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ):Response<DetailResponse?>

    @GET("3/movie/{id}/videos")
    suspend fun getVideos(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ) : Response<TrailerResponse?>

    @GET("3/movie/{id}/credits")
    suspend fun getMovieCrew(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ) : Response<PeopleDetailResponse?>

}