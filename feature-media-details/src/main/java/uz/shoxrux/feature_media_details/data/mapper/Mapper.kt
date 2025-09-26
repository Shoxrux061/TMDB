package uz.shoxrux.feature_media_details.data.mapper

import uz.shoxrux.feature_media_details.data.dto.movie_details.GenreDTO
import uz.shoxrux.feature_media_details.data.dto.movie_details.MovieDetailsResponseDTO
import uz.shoxrux.feature_media_details.data.dto.movie_details.ProductionCountryDTO
import uz.shoxrux.feature_media_details.data.dto.movie_details.SpokenLanguageDTO
import uz.shoxrux.feature_media_details.domain.models.movies.CountryUi
import uz.shoxrux.feature_media_details.domain.models.movies.GenreUi
import uz.shoxrux.feature_media_details.domain.models.movies.LanguageUi
import uz.shoxrux.feature_media_details.domain.models.movies.MovieDetailsUi

fun MovieDetailsResponseDTO.toDomain(): MovieDetailsUi {
    return MovieDetailsUi(
        id = id ?: 0,
        title = title.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        posterUrl = posterPath,
        backdropUrl = backdropPath,
        releaseDate = releaseDate.orEmpty(),
        runtimeMinutes = runtime,
        genres = genres.orEmpty().filterNotNull().map { it.toDomain() },
        productionCountries = productionCountries.orEmpty().filterNotNull().map { it.toDomain() },
        spokenLanguages = spokenLanguages.orEmpty().filterNotNull().map { it.toDomain() },
        rating = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        tagline = tagline?:""
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
