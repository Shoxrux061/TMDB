package uz.shoxrux.core.providers

interface LocalCacheProvider {

    suspend fun getLanguage(): String

    suspend fun isDark(): Boolean

    suspend fun setLanguage(lang: String)

    suspend fun setIsDark(isDark: Boolean)

    suspend fun getIsFirstLaunch(): Boolean

    suspend fun setIsFirstLaunch()

    suspend fun setSessionToken(token: String)

    suspend fun getSessionToken(): String?

}