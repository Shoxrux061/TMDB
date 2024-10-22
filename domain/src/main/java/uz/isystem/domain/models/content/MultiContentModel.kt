package uz.isystem.domain.models.content

import uz.isystem.domain.models.content.images.Backdrop
import uz.isystem.domain.models.content.images.Poster
import uz.isystem.domain.models.movie.movie_detail.Result

data class MultiContentModel(
    var posters: List<Poster>?,
    var backdrops: List<Backdrop>?,
    var videos: List<Result>?

)