package uz.shoxrux.feature_media.data.repository

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.handler.safeApiCall
import uz.shoxrux.feature_media.data.mapper.toDomain
import uz.shoxrux.feature_media.data.services.media_servcie.MediaService
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.SeriesType
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel
import uz.shoxrux.feature_media.domain.repository.MediaRepository
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val service: MediaService
) : MediaRepository {

    override suspend fun getMovies(
        type: MovieType,
        page: Int
    ): Flow<NetworkResult<List<MovieModel>>> =
        safeApiCall(
            apiCall = {
                when (type) {
                    MovieType.Trending -> {
                        service.getTrendingMovies(page)
                    }

                    else -> {
                        service.getMovieByCategories(
                            category = type.category,
                            page = page,
                        )
                    }
                }
            },
            mapper = { dto -> dto.body()?.results.orEmpty().mapNotNull { it?.toDomain() } }
        )

    override suspend fun getSeries(
        type: SeriesType,
        page: Int
    ): Flow<NetworkResult<List<SeriesResultModel>>> =
        safeApiCall(
            apiCall = {
                when (type) {
                    SeriesType.Trending -> {
                        service.getTvTrending(page)
                    }

                    else -> {
                        service.getSeriesByCategories(
                            category = type.category,
                            page = page,
                        )
                    }
                }
            },
            mapper = { dto -> dto.body()?.results.orEmpty().mapNotNull { it?.toDomain() } }
        )
}