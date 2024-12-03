package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.domain.models.people.details.credits.movie.MovieCreditsResponse
import uz.isystem.domain.models.people.details.credits.tv.TvCreditsResponse
import uz.isystem.domain.models.people.details.ids.ExternalIdsResponse
import uz.isystem.domain.models.people.details.images.PeopleImagesResponse

interface PeopleDetailService {

    @GET("3/person/{id}")
    suspend fun getPeopleDetails(
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Response<PeopleDetailsResponse?>

    @GET("3/person/{id}/images")
    suspend fun getPeopleImages(
        @Path("id") id: Int,
        @Query("api_key") key: String,
    ): Response<PeopleImagesResponse?>

    @GET("3/person/{id}/movie_credits")
    suspend fun getPeopleMovie(
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Response<MovieCreditsResponse?>

    @GET("3/person/{id}/tv_credits")
    suspend fun getPeopleTv(
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Response<TvCreditsResponse?>

    @GET("3/person/{id}/external_ids")
    suspend fun getPeopleIds(
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ) : Response<ExternalIdsResponse?>

}