package uz.shoxrux.feature_media.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.ui.handler.ErrorHandler
import uz.shoxrux.core.ui.handler.NetworkResult
import uz.shoxrux.di.BuildConfig
import uz.shoxrux.feature_media.data.mapper.toUIModel
import uz.shoxrux.feature_media.data.services.media_servcie.MediaService
import uz.shoxrux.feature_media.domain.models.SeriesType
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieResultModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel
import uz.shoxrux.feature_media.domain.repository.MediaRepository
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val service: MediaService
) : MediaRepository {

    override suspend fun getMovies(
        type: MovieType,
        page: Int
    ): Flow<NetworkResult<List<MovieResultModel>>> = flow {
        try {
            val result = when (type) {
                MovieType.NowPlaying -> service.getNowPlayingMovies(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                MovieType.Popular -> service.getPopularMovies(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                MovieType.TopRated -> service.getTopRatedMovies(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                MovieType.Trending -> service.getTrendingMovies(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                MovieType.Upcoming -> service.getUpcomingMovies(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )
            }

            emit(NetworkResult.Success(result.results.map { it.toUIModel() }))
        } catch (e: Exception) {
            emit(NetworkResult.Error(ErrorHandler.parse(e)))
        }
    }

    override suspend fun getSeries(
        type: SeriesType,
        page: Int
    ): Flow<NetworkResult<List<SeriesResultModel>>> = flow {

        try {

            val result = when (type) {
                SeriesType.Popular -> service.getTvPopular(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                SeriesType.Trending -> service.getTvTrending(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                SeriesType.TopRated -> service.getTvTopRated(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                SeriesType.OnTheAir -> service.getTvOnTheAir(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )

                SeriesType.AiringToday -> service.getTvAiringToday(
                    BuildConfig.TMDB_API_KEY, page, "ru"
                )
            }

            emit(NetworkResult.Success(result.results.map { it.toUIModel() }))

        } catch (e: Exception) {
            emit(NetworkResult.Error(ErrorHandler.parse(e)))
        }

    }
}