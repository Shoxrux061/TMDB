package uz.shoxrux.feature_media_details.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.shoxrux.feature_media_details.data.dto.movies.credits.MovieCreditsResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.details.MovieDetailsResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.images.MovieImagesResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.list.MovieListDTO
import uz.shoxrux.feature_media_details.data.dto.movies.review.MovieReviewsResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.videos.MovieVideosResponseDTO

interface MediaDetailsService {

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") id: Int
    ): Response<MovieDetailsResponseDTO?>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") id: Int
    ): Response<MovieCreditsResponseDTO?>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") id: Int
    ): Response<MovieListDTO?>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movie_id") id: Int
    ): Response<MovieListDTO?>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") id: Int
    ): Response<MovieReviewsResponseDTO?>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") id: Int
    ): Response<MovieVideosResponseDTO?>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") id: Int
    ): Response<MovieImagesResponseDTO?>
}