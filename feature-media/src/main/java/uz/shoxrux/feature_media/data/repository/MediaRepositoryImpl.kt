package uz.shoxrux.feature_media.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.ui.handler.NetworkResult
import uz.shoxrux.di.BuildConfig
import uz.shoxrux.feature_media.data.services.media_servcie.MediaService
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieListModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesListModel
import uz.shoxrux.feature_media.domain.repository.MediaRepository
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(private val service: MediaService) : MediaRepository {

    override suspend fun getMovies(
        type: MovieType,
        page: Int
    ): Flow<NetworkResult<List<MovieListModel>>> =
        flow {
            emit(NetworkResult.Loading)

            try {
                val result = when (type) {
                    MovieType.NowPlaying -> service.getNowPlayingMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        "ru"
                    )

                    MovieType.Popular -> service.getPopularMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        "ru"
                    )

                    MovieType.TopRated -> service.getTopRatedMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        "ru"
                    )

                    MovieType.Trending -> service.getTrendingMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        "ru"
                    )

                    MovieType.Upcoming -> service.getUpcomingMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        "ru"
                    )
                }

                emit(NetworkResult.Success(result))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.localizedMessage))
            }
        }

    override suspend fun getSeries(): Flow<NetworkResult<List<SeriesListModel>>> = flow {
        emit(NetworkResult.Loading)
    }
}