package uz.shoxrux.core.providers

import kotlinx.coroutines.flow.Flow

interface LocalCacheProvider {

    fun getLanguage(): Flow<String>
    fun isDark(): Flow<Boolean>
    fun getIsFirstLaunch(): Flow<Boolean>
    fun getSessionToken(): Flow<String?>

    suspend fun setLanguage(lang: String)
    suspend fun setIsDark(isDark: Boolean)
    suspend fun setIsFirstLaunch()
    suspend fun setSessionToken(token: String)
}