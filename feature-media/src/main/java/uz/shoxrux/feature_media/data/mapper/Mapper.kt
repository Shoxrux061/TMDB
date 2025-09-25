package uz.shoxrux.feature_media.data.mapper

import uz.shoxrux.core.utils.Constants
import uz.shoxrux.feature_media.data.dto.movie.MovieResultDTO
import uz.shoxrux.feature_media.data.dto.series.SeriesResultDTO
import uz.shoxrux.feature_media.domain.models.media_types.movies.MovieModel
import uz.shoxrux.feature_media.domain.models.media_types.series.SeriesResultModel
import java.util.Locale

fun MovieResultDTO.toDomain(): MovieModel {

    return MovieModel(
        adult = adult ?: false,
        backdropPath = (Constants.IMAGE_URL + this.backdropPath),
        genreIds = genreIds.orEmpty(),
        id = id ?: -1,
        originalLanguage = originalLanguage ?: "unknown",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = String.format(Locale.US, "%.1f", this.popularity),
        posterPath = (Constants.IMAGE_URL + this.posterPath),
        releaseDate = releaseDate ?: "",
        title = title ?: "Untitled",
        video = video ?: false,
        voteAverage = String.format(Locale.US, "%.1f", this.voteAverage),
        voteCount = voteCount ?: 0
    )

}

fun SeriesResultDTO.toDomain(): SeriesResultModel {

    return SeriesResultModel(
        adult = this.adult ?: false,
        backdropPath = (Constants.IMAGE_URL + this.backdropPath),
        firstAirDate = this.firstAirDate ?: "unknown",
        genreIds = this.genreIds.orEmpty(),
        id = this.id ?: -1,
        name = this.name ?: "unknown",
        originCountry = this.originCountry.orEmpty(),
        originalLanguage = this.originalLanguage ?: "",
        originalName = this.originalName ?: "",
        overview = this.overview ?: "",
        popularity = String.format(Locale.US, "%.1f", this.popularity),
        posterPath = (Constants.IMAGE_URL + this.posterPath),
        voteAverage = String.format(Locale.US, "%.1f", this.voteAverage),
        voteCount = this.voteCount ?: 0
    )

}