package uz.isystem.domain.repository.auth

import uz.isystem.utills.ResultWrapper

interface AuthRepository<C> {

    suspend fun createToken(apiKey: String): ResultWrapper<C?, Any?>

}