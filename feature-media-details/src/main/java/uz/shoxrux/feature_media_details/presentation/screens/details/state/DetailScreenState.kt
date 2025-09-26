package uz.shoxrux.feature_media_details.presentation.screens.details.state

import uz.shoxrux.core.handler.AppError
import uz.shoxrux.feature_media_details.domain.models.movies.MovieDetailsUi

data class DetailScreenState(

    val isLoading: Boolean = false,
    val error: AppError? = null,
    val movieDetails: MovieDetailsUi? = null

)
