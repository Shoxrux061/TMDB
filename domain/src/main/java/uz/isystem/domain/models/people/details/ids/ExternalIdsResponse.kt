package uz.isystem.domain.models.people.details.ids

data class ExternalIdsResponse(
    val facebook_id: String?,
    val freebase_id: String?,
    val freebase_mid: String?,
    val id: Int?,
    val imdb_id: String?,
    val instagram_id: String?,
    val tiktok_id: String?,
    val tvrage_id: Int?,
    val twitter_id: String?,
    val wikidata_id: String?,
    val youtube_id: String?
)