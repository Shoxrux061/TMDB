package uz.isystem.domain.models.people.people_list

data class PeopleListResponse(
    val page: Int,
    val results: List<PeopleResult>,
    val total_pages: Int,
    val total_results: Int
)