package uz.shoxrux.feature_media.domain.use_case.movies

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import uz.shoxrux.core.handler.AppError
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media.domain.models.media_types.MovieType
import uz.shoxrux.feature_media.presentation.screens.main.movies.state.MoviesBundle
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val getMoviesUseCase: GetMoviesByCategoryUseCase
) {
    suspend operator fun invoke(page: Int): Flow<NetworkResult<MoviesBundle>> = flow {
        coroutineScope {
            try {
                val results = listOf(
                    async {
                        MovieType.Trending to getMoviesUseCase(
                            MovieType.Trending,
                            page
                        ).first()
                    },
                    async {
                        MovieType.Popular to getMoviesUseCase(
                            MovieType.Popular,
                            page
                        ).first()
                    },
                    async {
                        MovieType.TopRated to getMoviesUseCase(
                            MovieType.TopRated,
                            page
                        ).first()
                    },
                    async {
                        MovieType.Upcoming to getMoviesUseCase(
                            MovieType.Upcoming,
                            page
                        ).first()
                    }
                ).awaitAll()

                val bundle = MoviesBundle(
                    trending = (results.find { it.first == MovieType.Trending }?.second as? NetworkResult.Success)?.data,
                    popular = (results.find { it.first == MovieType.Popular }?.second as? NetworkResult.Success)?.data,
                    topRated = (results.find { it.first == MovieType.TopRated }?.second as? NetworkResult.Success)?.data,
                    upcoming = (results.find { it.first == MovieType.Upcoming }?.second as? NetworkResult.Success)?.data
                )

                emit(NetworkResult.Success(bundle))
            } catch (e: Exception) {
                emit(NetworkResult.Error(AppError.Server(e.message)))
            }
        }
    }
}