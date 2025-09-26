package uz.shoxrux.feature_media_details.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.shoxrux.feature_media_details.data.dto.movie_details.MovieDetailsResponseDTO

interface MediaDetailsService {

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") id: Int
    ): Response<MovieDetailsResponseDTO?>

}