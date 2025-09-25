package uz.shoxrux.feature_media.domain.use_case.series

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.domain.models.media_types.SeriesType
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel
import uz.shoxrux.feature_media.presentation.screens.main.series.state.SeriesBundle

class GetAllSeriesUseCase(
    private val getSeriesUseCase: GetSeriesByCategoryUseCase
) {
    operator fun invoke(page: Int): Flow<NetworkResult<SeriesBundle>> = flow {

        try {

            val results = coroutineScope {
                listOf(
                    SeriesType.Trending to async {
                        getSeriesUseCase(
                            SeriesType.Trending,
                            page
                        ).first()
                    },
                    SeriesType.Popular to async {
                        getSeriesUseCase(
                            SeriesType.Popular,
                            page
                        ).first()
                    },
                    SeriesType.TopRated to async {
                        getSeriesUseCase(
                            SeriesType.TopRated,
                            page
                        ).first()
                    },
                    SeriesType.OnTheAir to async {
                        getSeriesUseCase(
                            SeriesType.OnTheAir,
                            page
                        ).first()
                    },

                    SeriesType.AiringToday to async {
                        getSeriesUseCase(
                            SeriesType.AiringToday,
                            page
                        ).first()
                    }
                ).associate { (type, deferred) -> type to deferred.await() }
            }

            val bundle = SeriesBundle(
                trending = (results[SeriesType.Trending] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                popular = (results[SeriesType.Popular] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                topRated = (results[SeriesType.TopRated] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                onTheAir = (results[SeriesType.OnTheAir] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                airingToday = (results[SeriesType.AiringToday] as? NetworkResult.Success)?.data
                    ?: emptyList()
            )

            emit(NetworkResult.Success(bundle))

        } catch (e: Exception) {
            emit(NetworkResult.Error(AppError.Server(e.message)))
        }

    }

}