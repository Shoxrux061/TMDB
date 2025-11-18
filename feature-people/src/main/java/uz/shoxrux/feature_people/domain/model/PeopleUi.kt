package uz.shoxrux.feature_people.domain.model

data class PeopleUi(
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val profilePath: String
)