package uz.shoxrux.feature_people.data.mapper

import uz.shoxrux.core.utils.Constants
import uz.shoxrux.feature_people.data.dto.people.PeopleResultDto
import uz.shoxrux.feature_people.domain.model.PeopleUi

fun PeopleResultDto.toDomain(): PeopleUi {

    return PeopleUi(
        gender = gender ?: -1,
        id = id ?: -1,
        knownForDepartment = knownForDepartment ?: "unknonwn",
        name = name ?: "",
        originalName = originalName ?: "",
        profilePath = Constants.IMAGE_URL.plus("/$profilePath")
    )

}