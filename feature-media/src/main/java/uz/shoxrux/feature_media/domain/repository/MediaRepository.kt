package uz.shoxrux.feature_media.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.SeriesType
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieResultModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel

interface MediaRepository {

    suspend fun getMovies(
        type: MovieType,
        page: Int
    ): Flow<NetworkResult<List<MovieResultModel>>>

    suspend fun getSeries(
        type: SeriesType,
        page: Int
    ): Flow<NetworkResult<List<SeriesResultModel>>>

}