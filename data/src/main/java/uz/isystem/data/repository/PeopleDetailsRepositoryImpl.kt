package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.PeopleDetailService
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.domain.models.people.details.credits.movie.MovieCreditsResponse
import uz.isystem.domain.models.people.details.credits.tv.TvCreditsResponse
import uz.isystem.domain.models.people.details.ids.ExternalIdsResponse
import uz.isystem.domain.models.people.details.images.PeopleImagesResponse
import uz.isystem.domain.repository.people.details.PeopleDetailsRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class PeopleDetailsRepositoryImpl @Inject constructor(private val service: PeopleDetailService) :
    PeopleDetailsRepository<PeopleDetailsResponse?, PeopleImagesResponse?, MovieCreditsResponse?, TvCreditsResponse?, ExternalIdsResponse?> {


    override suspend fun getPeopleDetails(
        id: Int,
        key: String,
        lang: String
    ): ResultWrapper<PeopleDetailsResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPeopleDetails(id = id, lang = lang, key = key)
        }
    }

    override suspend fun getPeopleImages(
        id: Int,
        key: String,
    ): ResultWrapper<PeopleImagesResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPeopleImages(id = id, key = key)
        }
    }

    override suspend fun getMovieCredits(
        id: Int,
        key: String,
        lang: String
    ): ResultWrapper<MovieCreditsResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPeopleMovie(id = id, key = key, lang = lang)
        }
    }

    override suspend fun getTvCredits(
        id: Int,
        key: String,
        lang: String
    ): ResultWrapper<TvCreditsResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPeopleTv(id = id, key = key, lang = lang)
        }
    }

    override suspend fun getPeopleLinks(
        id: Int,
        key: String,
        lang: String
    ): ResultWrapper<ExternalIdsResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPeopleIds(id = id, key = key, lang = lang)
        }
    }

}