package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.domain.models.movie.movie_list.MovieListResponse
import uz.isystem.domain.models.people.people_list.PeopleListResponse
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse

interface SearchService {

    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("query") q: String
    ): Response<MovieListResponse?>

    @GET("3/search/tv")
    suspend fun searchSerial(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("query") q: String
    ): Response<SeriesResponse?>

    @GET("3/search/person")
    suspend fun searchPerson(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("query") q: String
    ): Response<PeopleListResponse?>

}