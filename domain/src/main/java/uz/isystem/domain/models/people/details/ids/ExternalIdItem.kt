package uz.isystem.domain.models.people.details.ids

data class ExternalIdItem(
    val platform: Platform,
    val id: String?
)

enum class Platform {
    Facebook, Instagram, YouTube, TikTok, HomePage, X, Wiki, IMDB
}
