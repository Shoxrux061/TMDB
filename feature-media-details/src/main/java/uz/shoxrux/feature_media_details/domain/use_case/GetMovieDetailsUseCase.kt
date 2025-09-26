package uz.shoxrux.feature_media_details.domain.use_case

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media_details.domain.models.movies.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.repository.MediaDetailsRepository

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MediaDetailsRepository
) {

    suspend operator fun invoke(id: Int): Flow<NetworkResult<MovieDetailsUi?>> {

        return repository.getMovieDetails(id)

    }
}