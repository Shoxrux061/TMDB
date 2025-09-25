package uz.shoxrux.feature_media.domain.use_case.series

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media.domain.models.media_types.SeriesType
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel
import uz.shoxrux.feature_media.domain.repository.MediaRepository

class GetSeriesByCategoryUseCase(
    private val repository: MediaRepository
) {

    suspend operator fun invoke(
        type: SeriesType,
        page: Int
    ): Flow<NetworkResult<List<SeriesResultModel>>> {

        if (page <= 0) {
            return flowOf(
                NetworkResult.Error(AppError.Validation)
            )
        }

        return repository.getSeries(type, page)
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