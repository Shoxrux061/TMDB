package uz.shoxrux.feature_media_details.domain.use_case.movie

import uz.shoxrux.feature_media_details.domain.repository.MediaDetailsRepository
import javax.inject.Inject

class GetMovieReviewUseCase @Inject constructor(
    private val repository: MediaDetailsRepository
) {
    suspend operator fun invoke(id: Int) = repository.getMovieReviews(id)
}