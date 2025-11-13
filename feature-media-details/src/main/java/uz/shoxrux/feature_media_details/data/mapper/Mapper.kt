package uz.shoxrux.feature_media_details.data.mapper

import uz.shoxrux.core.utils.Constants
import uz.shoxrux.feature_media_details.data.dto.movies.credits.CastDTO
import uz.shoxrux.feature_media_details.data.dto.movies.credits.CrewDTO
import uz.shoxrux.feature_media_details.data.dto.movies.credits.MovieCreditsResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.details.GenreDTO
import uz.shoxrux.feature_media_details.data.dto.movies.details.MovieDetailsResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.details.ProductionCountryDTO
import uz.shoxrux.feature_media_details.data.dto.movies.details.SpokenLanguageDTO
import uz.shoxrux.feature_media_details.data.dto.movies.images.ImageDTO
import uz.shoxrux.feature_media_details.data.dto.movies.images.MovieImagesResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.list.MovieResultDTO
import uz.shoxrux.feature_media_details.data.dto.movies.review.ReviewResultDTO
import uz.shoxrux.feature_media_details.data.dto.movies.review.MovieReviewsResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.videos.MovieVideosResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movies.videos.MovieVideosResultDTO
import uz.shoxrux.feature_media_details.domain.models.movies.MovieUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.images.MovieImagesUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.video.MovieVideoUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.CountryUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.GenreUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.LanguageUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.MovieCreditsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.PersonUi
import uz.shoxrux.feature_media_details.domain.models.movies.review.MovieReviewUi
import java.util.Locale

fun MovieDetailsResponseDTO.toDomain(): MovieDetailsUi {
    return MovieDetailsUi(
        id = id ?: 0,
        title = title.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        posterUrl = Constants.IMAGE_URL.plus(posterPath),
        backdropUrl = Constants.IMAGE_URL.plus(backdropPath),
        releaseDate = releaseDate.orEmpty(),
        runtimeMinutes = "${runtime?.div(60)} h ${runtime?.rem(60)} m",
        genres = genres.orEmpty().filterNotNull().map { it.toDomain() },
        productionCountries = productionCountries.orEmpty().filterNotNull().map { it.toDomain() },
        spokenLanguage = spokenLanguages.orEmpty().filterNotNull()
            .map { it.toDomain() }[0].englishName.substring(0, 2).uppercase(),
        rating = String.format(Locale.US, "%.1f", this.voteAverage),
        voteCount = voteCount ?: 0,
        tagline = tagline ?: "",
        isRealised = status == "Released"
    )
}

// GenreDTO -> GenreUi
fun GenreDTO.toDomain(): GenreUi {
    return GenreUi(
        id = id ?: 0,
        name = name.orEmpty()
    )
}

// ProductionCountryDTO -> CountryUi
fun ProductionCountryDTO.toDomain(): CountryUi {
    return CountryUi(
        code = iso31661.orEmpty(),
        name = name.orEmpty()
    )
}

// SpokenLanguageDTO -> LanguageUi
fun SpokenLanguageDTO.toDomain(): LanguageUi {
    return LanguageUi(
        code = iso6391.orEmpty(),
        englishName = englishName.orEmpty(),
        localName = name
    )
}

fun CastDTO.toDomain(): PersonUi {
    val safeId = id ?: return PersonUi()
    return PersonUi(
        id = safeId,
        name = name.orEmpty(),
        role = character.orEmpty(),
        profilePath = Constants.IMAGE_URL.plus(profilePath)
    )
}

fun CrewDTO.toDomain(): PersonUi {
    val safeId = id ?: return PersonUi()
    return PersonUi(
        id = safeId,
        name = name.orEmpty(),
        role = job.orEmpty(),
        profilePath = Constants.IMAGE_URL.plus(profilePath)
    )
}

fun MovieCreditsResponseDTO.toDomain(): MovieCreditsUi {

    val cast = cast.orEmpty().filterNotNull().map { it.toDomain() }
    val crew = crew.orEmpty().filterNotNull().map { it.toDomain() }

    return MovieCreditsUi(
        cast = cast,
        crew = crew
    )

}

fun MovieImagesResponseDTO.toDomain(): MovieImagesUi {

    val backdrops = backdrops.orEmpty().filterNotNull().map { it.toDomain() }
    val posters = posters.orEmpty().filterNotNull().map { it.toDomain() }

    return MovieImagesUi(
        backdrops = backdrops,
        posters = posters
    )

}

fun ImageDTO.toDomain(): String {
    return Constants.IMAGE_URL.plus(this.filePath)
}

fun MovieReviewsResponseDTO.toDomain(): List<MovieReviewUi> {
    return results.orEmpty().mapNotNull { it?.toDomain() }
}

fun ReviewResultDTO.toDomain(): MovieReviewUi? {
    val text = content?.takeIf { it.isNotBlank() } ?: return null
    return MovieReviewUi(
        author = author ?: authorDetails?.username ?: "Unknown",
        avatarUrl = authorDetails?.avatarPath?.let {
            if (it.startsWith("/")) "https://image.tmdb.org/t/p/w200$it" else it
        },
        rating = authorDetails?.rating,
        content = text,
        createdAt = createdAt ?: ""
    )
}

fun MovieVideosResponseDTO.toDomain(): List<MovieVideoUi> {
    return results.orEmpty().mapNotNull { it?.toDomain() }
}

fun MovieVideosResultDTO.toDomain(): MovieVideoUi? {
    val videoKey = key ?: return null
    return MovieVideoUi(
        key = videoKey,
        imageUrl = "https://img.youtube.com/vi/$videoKey/hqdefault.jpg"
    )
}

fun MovieResultDTO.toDomain(): MovieUi {

    return MovieUi(
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