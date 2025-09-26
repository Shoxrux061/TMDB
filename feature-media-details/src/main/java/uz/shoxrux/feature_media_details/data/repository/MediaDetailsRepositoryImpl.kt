package uz.shoxrux.feature_media_details.data.repository

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.core.handler.safeApiCall
import uz.shoxrux.feature_media_details.data.mapper.toDomain
import uz.shoxrux.feature_media_details.data.service.MediaDetailsService
import uz.shoxrux.feature_media_details.domain.models.movies.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.repository.MediaDetailsRepository

class MediaDetailsRepositoryImpl @Inject constructor(
    private val service: MediaDetailsService
) : MediaDetailsRepository {

    override suspend fun getMovieDetails(id: Int): Flow<NetworkResult<MovieDetailsUi?>> =
        safeApiCall(

            apiCall = {
                service.getMovieById(id)
            },
            mapper = { dto -> dto.body()?.toDomain() }

        )

}