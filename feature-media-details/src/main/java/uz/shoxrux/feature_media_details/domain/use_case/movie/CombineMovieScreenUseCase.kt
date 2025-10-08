package uz.shoxrux.feature_media_details.domain.use_case.movie

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media_details.domain.models.movies.MovieInfoUi
import uz.shoxrux.feature_media_details.domain.models.movies.MovieUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.MovieContentUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.images.MovieImagesUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.video.MovieVideoUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.MovieCreditsUi
import uz.shoxrux.feature_media_details.domain.models.movies.review.MovieReviewUi
import javax.inject.Inject
import kotlin.collections.orEmpty

class CombineMovieScreenUseCase @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val getMovieSimilarUseCase: GetMovieSimilarUseCase,
    private val getMovieRecommendationUseCase: GetMovieRecommendationUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewUseCase,
    private val getMovieVideosUseCase: GetMovieVideosUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase
) {

    suspend operator fun invoke(id: Int): NetworkResult<MovieInfoUi> = coroutineScope {
        try {

            //Запускаем асинхронные задачи для каждого типа данных

            val details = async { getMovieDetailsUseCase(id) }
            val credits = async { getMovieCreditsUseCase(id) }
            val similar = async { getMovieSimilarUseCase(id) }
            val recommended = async { getMovieRecommendationUseCase(id) }
            val reviews = async { getMovieReviewsUseCase(id) }
            val videos = async { getMovieVideosUseCase(id) }
            val images = async { getMovieImagesUseCase(id) }

            // Дожидаемся результатов
            val detailsResult = details.await()
            val creditsResult = credits.await()
            val similarResult = similar.await()
            val recommendedResult = recommended.await()
            val reviewsResult = reviews.await()
            val videosResult = videos.await()
            val imagesResult = images.await()

            listOf(
                detailsResult, creditsResult, similarResult, recommendedResult,
                reviewsResult, videosResult, imagesResult
            ).filterIsInstance<NetworkResult.Error>()
                .firstOrNull()
                ?.let { return@coroutineScope it }

            // Собираем данные в один объект MovieInfoUi
            val movieInfoUi = combineResults(
                details = (detailsResult as NetworkResult.Success).data,
                credits = (creditsResult as NetworkResult.Success).data,
                similar = (similarResult as NetworkResult.Success).data,
                recommended = (recommendedResult as NetworkResult.Success).data,
                reviews = (reviewsResult as NetworkResult.Success).data,
                videos = (videosResult as NetworkResult.Success).data,
                images = (imagesResult as NetworkResult.Success).data
            )

            NetworkResult.Success(movieInfoUi)
        } catch (e: Exception) {
            NetworkResult.Error(AppError.Unknown(e))
        }
    }

    // Комбинируем результаты в один объект MovieInfoUi
    private fun combineResults(
        details: MovieDetailsUi?,
        credits: MovieCreditsUi?,
        similar: List<MovieUi>?,
        recommended: List<MovieUi>?,
        reviews: List<MovieReviewUi>?,
        videos: List<MovieVideoUi>?,
        images: MovieImagesUi?
    ) = MovieInfoUi(
        detailsUi = details,
        creditsUi = credits,
        similarMovies = similar.orEmpty(),
        recommendedMovies = recommended.orEmpty(),
        content = MovieContentUi(
            posters = images?.posters.orEmpty(),
            backdrops = images?.backdrops.orEmpty(),
            videos = videos.orEmpty()
        ),
        reviews = reviews.orEmpty()
    )
}