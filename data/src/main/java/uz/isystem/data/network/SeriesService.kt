package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.domain.models.tv_series_list.SeriesResponse

interface SeriesService {

    @GET("/3/tv/airing_today")
    suspend fun getAiringToday(
        @Query("api_key") apiKey:String,
        @Query("page") page:Int,
        @Query("language") lang:String
    ) : Response<SeriesResponse?>

}