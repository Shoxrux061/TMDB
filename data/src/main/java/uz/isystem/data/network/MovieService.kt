package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.domain.models.movie.movie_list.MovieListResponse

interface MovieService {

    @GET("3/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieListResponse?>

    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieListResponse?>

    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieListResponse?>

    @GET("3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Response<MovieListResponse?>

    @GET("3/trending/movie/week")
    suspend fun getTrending(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ): Response<MovieListResponse?>
}