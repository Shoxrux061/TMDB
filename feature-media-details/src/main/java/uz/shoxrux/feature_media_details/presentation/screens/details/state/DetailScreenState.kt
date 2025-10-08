package uz.shoxrux.feature_media_details.presentation.screens.details.state

import uz.shoxrux.core.handler.AppError
import uz.shoxrux.feature_media_details.domain.models.movies.MovieUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.MovieContentUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.MovieCreditsUi
import uz.shoxrux.feature_media_details.domain.models.movies.review.MovieReviewUi

data class DetailScreenState(

    val isLoading: Boolean = false,
    val error: AppError? = null,
    val movieDetails: MovieDetailsUi? = null,
    val movieCredits: MovieCreditsUi? = null,
    val similarMovies: List<MovieUi> = emptyList(),
    val recommendedMovies: List<MovieUi> = emptyList(),
    val reviews: List<MovieReviewUi> = emptyList(),
    val contentUi: MovieContentUi? = null

)
