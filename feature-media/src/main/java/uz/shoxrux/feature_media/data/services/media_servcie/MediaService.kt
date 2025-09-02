package uz.shoxrux.feature_media.data.services.media_servcie

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.shoxrux.feature_media.data.dto.movie.MovieListDTO
import uz.shoxrux.feature_media.data.dto.series.SeriesListDTO

interface MediaService {

    @GET("3/movie/{category}")
    suspend fun getMovieByCategories(
        @Path("category") category: String,
        @Query("page") page: Int
    ): MovieListDTO

    @GET("3/trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("page") page: Int
    ): MovieListDTO

    @GET("3/tv/{category}")
    suspend fun getSeriesByCategories(
        @Path("category") category: String,
        @Query("page") page: Int
    ): SeriesListDTO

    @GET("3/trending/tv/day")
    suspend fun getTvTrending(
        @Query("page") page: Int
    ): SeriesListDTO


}