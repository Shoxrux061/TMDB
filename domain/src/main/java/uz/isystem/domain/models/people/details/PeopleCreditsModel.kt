package uz.isystem.domain.models.people.details

import uz.isystem.domain.models.people.details.credits.movie.Cast

data class PeopleCreditsModel(
    var movies: List<Cast>? = null,
    var tv: List<uz.isystem.domain.models.people.details.credits.tv.Cast>? = null
)
