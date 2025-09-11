package uz.shoxrux.feature_media.domain.use_case.movies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieModel
import uz.shoxrux.feature_media.domain.repository.MediaRepository
import javax.inject.Inject

class GetMoviesByCategoryUseCase @Inject constructor(
    private val mediaRepository: MediaRepository
) {

    suspend operator fun invoke(type: MovieType, page: Int): Flow<NetworkResult<List<MovieModel>>> {

        if (page <= 0) {
            return flowOf(
                NetworkResult.Error(AppError.Validation)
            )
        }

        return mediaRepository.getMovies(type, page)
            .map { result ->
                when (result) {
                    is NetworkResult.Success -> {

                        val cleanData = result.data
                        if (cleanData.isEmpty()) {
                            NetworkResult.Error(AppError.Validation)
                        } else {
                            NetworkResult.Success(cleanData)
                        }
                    }

                    is NetworkResult.Error -> result
                }
            }
            .catch { e ->
                emit(NetworkResult.Error(AppError.Server(e.message)))
            }
    }
}
