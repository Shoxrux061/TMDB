package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.domain.data.MovieListResponse

interface MovieService {

    @GET("3/movie/now_playing")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieListResponse?>
}