package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.domain.models.movie.movie_list.MovieListResponse

interface SearchService {

    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("query") q: String
    ): Response<MovieListResponse?>

}