package uz.isystem.data.repository

import kotlinx.coroutines.Dispatchers
import uz.isystem.data.network.AuthService
import uz.isystem.domain.models.auth.CreateTokenResponse
import uz.isystem.domain.repository.auth.AuthRepository
import uz.isystem.utills.ResultWrapper
import uz.isystem.utills.parseResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val service: AuthService) :
    AuthRepository<CreateTokenResponse?> {
    override suspend fun createToken(apiKey: String): ResultWrapper<CreateTokenResponse?, Any?> {

        return parseResponse(Dispatchers.IO) {
            service.createToken(apiKey)
        }
    }
}