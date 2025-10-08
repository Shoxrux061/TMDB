package uz.shoxrux.feature_media_details.domain.models.movies.content

import uz.shoxrux.feature_media_details.domain.models.movies.content.images.MovieImagesUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.video.MovieVideoUi

class MovieContentUi(
    val posters: List<String?> = emptyList(),
    val backdrops: List<String?> = emptyList(),
    val videos: List<MovieVideoUi> = emptyList(),
)