package uz.shoxrux.feature_people.presentation.screens.people.state

import uz.shoxrux.core.handler.AppError
import uz.shoxrux.feature_people.domain.model.PeopleUi

data class PeopleScreenState(

    val isLoading: Boolean = false,
    val peopleList: List<PeopleUi> = emptyList(),
    val error: AppError? = null,
    val currentPage: Int = 1

)