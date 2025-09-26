package uz.shoxrux.feature_media_details.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.shoxrux.core.handler.NetworkResult
import uz.shoxrux.feature_media_details.data.dto.movie_details.MovieDetailsResponseDTO
import uz.shoxrux.feature_media_details.domain.models.movies.MovieDetailsUi

interface MediaDetailsRepository {

    suspend fun getMovieDetails(id: Int): Flow<NetworkResult<MovieDetailsUi?>>

}