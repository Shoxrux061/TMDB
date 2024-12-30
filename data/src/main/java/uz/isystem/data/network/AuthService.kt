package uz.isystem.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.domain.models.auth.CreateTokenResponse

interface AuthService {
    @GET("3/authentication/token/new")
    suspend fun createToken(
        @Query("api_key") key: String
    ): Response<CreateTokenResponse?>
}