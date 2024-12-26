package uz.isystem.domain.models.other

import uz.isystem.domain.models.movie.movie_list.MovieListResponse
import uz.isystem.domain.models.people.people_list.PeopleListResponse
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse

data class SearchModel(
    var movieData: MovieListResponse? = null,
    var serialData: SeriesResponse? = null,
    var peopleData: PeopleListResponse? = null
)
