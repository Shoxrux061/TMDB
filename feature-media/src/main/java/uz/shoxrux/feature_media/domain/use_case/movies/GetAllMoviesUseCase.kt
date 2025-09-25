package uz.shoxrux.feature_media.domain.use_case.movies

import kotlinx.coroutines.async
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
    operator fun invoke(page: Int): Flow<NetworkResult<MoviesBundle>> = flow {
        try {

            val results = coroutineScope {
                listOf(
                    MovieType.Trending to async {
                        getMoviesUseCase(
                            MovieType.Trending,
                            page
                        ).first()
                    },
                    MovieType.Popular to async {
                        getMoviesUseCase(
                            MovieType.Popular,
                            page
                        ).first()
                    },
                    MovieType.TopRated to async {
                        getMoviesUseCase(
                            MovieType.TopRated,
                            page
                        ).first()
                    },
                    MovieType.Upcoming to async {
                        getMoviesUseCase(
                            MovieType.Upcoming,
                            page
                        ).first()
                    },

                    MovieType.NowPlaying to async {
                        getMoviesUseCase(
                            MovieType.NowPlaying,
                            page
                        ).first()
                    }
                ).associate { (type, deferred) -> type to deferred.await() }
            }

            val bundle = MoviesBundle(
                trending = (results[MovieType.Trending] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                popular = (results[MovieType.Popular] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                topRated = (results[MovieType.TopRated] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                upcoming = (results[MovieType.Upcoming] as? NetworkResult.Success)?.data
                    ?: emptyList(),
                nowPlaying = (results[MovieType.NowPlaying] as? NetworkResult.Success)?.data
                    ?: emptyList()
            )

            emit(NetworkResult.Success(bundle))
        } catch (e: Exception) {
            emit(NetworkResult.Error(AppError.Server(e.message)))
        }
    }

}