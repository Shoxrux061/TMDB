package uz.shoxrux.feature_media_details.domain.use_case.movie

import uz.shoxrux.feature_media_details.domain.repository.MediaDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MediaDetailsRepository
) {
    suspend operator fun invoke(id: Int) = repository.getMovieDetails(id)
}