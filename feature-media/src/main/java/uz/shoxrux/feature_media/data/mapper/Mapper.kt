package uz.shoxrux.feature_media.data.mapper

import uz.shoxrux.feature_media.data.dto.movie.MovieResultDTO
import uz.shoxrux.feature_media.data.dto.series.SeriesResultDTO
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel

fun MovieResultDTO.toDomain(): MovieModel {

    return MovieModel(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds.orEmpty(),
        id = id ?: -1,
        originalLanguage = originalLanguage ?: "unknown",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "Untitled",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )

}

fun SeriesResultDTO.toDomain(): SeriesResultModel {

    return SeriesResultModel(
        adult = this.adult ?: false,
        backdropPath = this.backdropPath ?: "",
        firstAirDate = this.firstAirDate ?: "unknown",
        genreIds = this.genreIds.orEmpty(),
        id = this.id ?: -1,
        name = this.name ?: "unknown",
        originCountry = this.originCountry.orEmpty(),
        originalLanguage = this.originalLanguage ?: "",
        originalName = this.originalName ?: "",
        overview = this.overview ?: "",
        popularity = this.popularity ?: 0.0,
        posterPath = this.posterPath ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        voteCount = this.voteCount ?: 0
    )

}