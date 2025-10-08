package uz.shoxrux.feature_media_details.domain.models.movies

import uz.shoxrux.feature_media_details.domain.models.movies.content.MovieContentUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.MovieCreditsUi
import uz.shoxrux.feature_media_details.domain.models.movies.review.MovieReviewUi

data class MovieInfoUi(
    val detailsUi: MovieDetailsUi? = null,
    val creditsUi: MovieCreditsUi? = null,
    val similarMovies: List<MovieUi> = emptyList(),
    val recommendedMovies: List<MovieUi> = emptyList(),
    val content: MovieContentUi? = null,
    val reviews: List<MovieReviewUi> = emptyList()
)