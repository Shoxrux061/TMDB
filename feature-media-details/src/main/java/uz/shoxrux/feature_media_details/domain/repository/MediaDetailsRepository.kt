package uz.shoxrux.feature_media_details.domain.repository

import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media_details.domain.models.movies.MovieUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.images.MovieImagesUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.video.MovieVideoUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.MovieCreditsUi
import uz.shoxrux.feature_media_details.domain.models.movies.review.MovieReviewUi

interface MediaDetailsRepository {

    suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailsUi?>

    suspend fun getMovieCredits(id: Int): NetworkResult<MovieCreditsUi?>

    suspend fun getMovieReviews(id: Int): NetworkResult<List<MovieReviewUi>?>

    suspend fun getMovieVideos(id: Int): NetworkResult<List<MovieVideoUi>?>

    suspend fun getMovieImages(id: Int): NetworkResult<MovieImagesUi?>

    suspend fun getMovieSimilar(id: Int): NetworkResult<List<MovieUi>?>

    suspend fun getMovieRecommended(id: Int): NetworkResult<List<MovieUi>?>
}