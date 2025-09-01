package uz.shoxrux.feature_media.data.services.media_servcie

import retrofit2.http.GET
import retrofit2.http.Query
import uz.shoxrux.feature_media.data.dto.movie.MovieListDTO
import uz.shoxrux.feature_media.data.dto.series.SeriesListDTO

interface MediaService {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieListDTO

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieListDTO

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieListDTO

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieListDTO

    @GET("3/trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieListDTO

    @GET("3/tv/airing_today")
    suspend fun getTvAiringToday(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): SeriesListDTO

    @GET("3/tv/on_the_air")
    suspend fun getTvOnTheAir(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): SeriesListDTO

    @GET("3/tv/popular")
    suspend fun getTvPopular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): SeriesListDTO

    @GET("3/tv/top_rated")
    suspend fun getTvTopRated(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): SeriesListDTO

    @GET("3/trending/tv/day")
    suspend fun getTvTrending(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): SeriesListDTO

}