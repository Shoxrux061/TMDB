package uz.isystem.domain.models.auth

data class CreateTokenResponse(
    val expires_at: String?,
    val request_token: String?,
    val success: Boolean?
)