package uz.shoxrux.feature_media.data.mapper

import uz.shoxrux.feature_media.data.dto.movie.MovieResultDTO
import uz.shoxrux.feature_media.data.dto.series.SeriesResultDTO
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieResultModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel

fun MovieResultDTO.toUIModel(): MovieResultModel {

    return MovieResultModel(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

}

fun SeriesResultDTO.toUIModel(): SeriesResultModel {

    return SeriesResultModel(
        adult = this.adult,
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        genreIds = this.genreIds,
        id = this.id,
        name = this.name,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

}