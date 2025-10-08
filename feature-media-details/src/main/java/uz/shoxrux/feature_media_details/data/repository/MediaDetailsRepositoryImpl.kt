package uz.shoxrux.feature_media_details.data.repository

import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.utils.safeApiCall
import uz.shoxrux.feature_media_details.data.mapper.toDomain
import uz.shoxrux.feature_media_details.data.service.MediaDetailsService
import uz.shoxrux.feature_media_details.domain.models.movies.MovieUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.images.MovieImagesUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.video.MovieVideoUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.MovieCreditsUi
import uz.shoxrux.feature_media_details.domain.models.movies.review.MovieReviewUi
import uz.shoxrux.feature_media_details.domain.repository.MediaDetailsRepository
import javax.inject.Inject

class MediaDetailsRepositoryImpl @Inject constructor(
    private val service: MediaDetailsService
) : MediaDetailsRepository {

    override suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailsUi?> =
        safeApiCall(

            apiCall = {
                service.getMovieById(id)
            },
            mapper = { dto ->
                dto?.toDomain()
            }

        )

    override suspend fun getMovieCredits(id: Int): NetworkResult<MovieCreditsUi?> =
        safeApiCall(

            apiCall = {
                service.getMovieCredits(id)
            },
            mapper = { dto ->
                dto?.toDomain()
            }

        )

    override suspend fun getMovieReviews(id: Int): NetworkResult<List<MovieReviewUi>?> =
        safeApiCall(

            apiCall = {
                service.getMovieReviews(id)
            },
            mapper = { dto ->
                dto?.results?.mapNotNull { dto ->
                    dto?.toDomain()
                }
            }

        )

    override suspend fun getMovieVideos(id: Int): NetworkResult<List<MovieVideoUi>?> =
        safeApiCall(

            apiCall = {
                service.getMovieVideos(id)
            },
            mapper = { dto ->
                dto?.results?.mapNotNull { dto ->
                    dto?.toDomain()
                }
            }

        )

    override suspend fun getMovieImages(id: Int): NetworkResult<MovieImagesUi?> =
        safeApiCall(

            apiCall = {
                service.getMovieImages(id)
            },
            mapper = { dto ->
                dto?.toDomain()
            }

        )

    override suspend fun getMovieSimilar(id: Int): NetworkResult<List<MovieUi>?> =
        safeApiCall(
            apiCall = {
                service.getSimilarMovies(id)
            },
            mapper = { dto ->
                dto?.results?.mapNotNull { dto ->
                    dto?.toDomain()
                }
            }
        )

    override suspend fun getMovieRecommended(id: Int): NetworkResult<List<MovieUi>?> =
        safeApiCall(
            apiCall = {
                service.getRecommendedMovies(id)
            },
            mapper = { dto ->
                dto?.results?.mapNotNull { dto ->
                    dto?.toDomain()
                }
            }
        )
}