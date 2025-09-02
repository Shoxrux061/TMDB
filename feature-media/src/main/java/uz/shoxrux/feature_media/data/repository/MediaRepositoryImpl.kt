package uz.shoxrux.feature_media.data.repository

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.handler.safeApiCall
import uz.shoxrux.core.providers.LocalCacheProvider
import uz.shoxrux.di.BuildConfig
import uz.shoxrux.feature_media.data.mapper.toUIModel
import uz.shoxrux.feature_media.data.services.media_servcie.MediaService
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.SeriesType
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieResultModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel
import uz.shoxrux.feature_media.domain.repository.MediaRepository
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val service: MediaService,
    private val localCache: LocalCacheProvider
) : MediaRepository {

    override suspend fun getMovies(
        type: MovieType,
        page: Int
    ): Flow<NetworkResult<List<MovieResultModel>>> =
        safeApiCall(
            apiCall = {
                when (type) {
                    MovieType.NowPlaying -> service.getNowPlayingMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    MovieType.Popular -> service.getPopularMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    MovieType.TopRated -> service.getTopRatedMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    MovieType.Trending -> service.getTrendingMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    MovieType.Upcoming -> service.getUpcomingMovies(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )
                }
            },
            mapper = { dto -> dto.results.map { it.toUIModel() } }
        )

    override suspend fun getSeries(
        type: SeriesType,
        page: Int
    ): Flow<NetworkResult<List<SeriesResultModel>>> =
        safeApiCall(
            apiCall = {
                when (type) {
                    SeriesType.Popular -> service.getTvPopular(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    SeriesType.Trending -> service.getTvTrending(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    SeriesType.TopRated -> service.getTvTopRated(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    SeriesType.OnTheAir -> service.getTvOnTheAir(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )

                    SeriesType.AiringToday -> service.getTvAiringToday(
                        BuildConfig.TMDB_API_KEY,
                        page,
                        localCache.getLanguage()
                    )
                }
            },
            mapper = { dto -> dto.results.map { it.toUIModel() } }
        )
}