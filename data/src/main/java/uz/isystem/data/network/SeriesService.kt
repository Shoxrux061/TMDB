package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse

interface SeriesService {

    @GET("/3/tv/airing_today")
    suspend fun getAiringToday(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<SeriesResponse?>


    @GET("/3/trending/tv/week")
    suspend fun getTrendingTv(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ): Response<SeriesResponse?>


    @GET("/3/tv/on_the_air")
    suspend fun getOnTheAir(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<SeriesResponse?>


    @GET("/3/tv/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<SeriesResponse?>


    @GET("/3/tv/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<SeriesResponse?>

}