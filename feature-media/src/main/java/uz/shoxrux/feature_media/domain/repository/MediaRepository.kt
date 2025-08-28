package uz.shoxrux.feature_media.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.ui.handler.NetworkResult
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieListModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesListModel

interface MediaRepository {

    suspend fun getMovies(
        type: MovieType,
        page: Int
    ): Flow<NetworkResult<List<MovieListModel>>>

    suspend fun getSeries(): Flow<NetworkResult<List<SeriesListModel>>>

}