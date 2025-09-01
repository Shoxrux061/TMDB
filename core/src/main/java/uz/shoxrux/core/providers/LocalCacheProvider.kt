package uz.shoxrux.core.providers

interface LocalCacheProvider {

    fun getLanguage(): String

    fun isDark(): Boolean

    fun setLanguage(lang: String)

    fun setIsDark(isDarl: Boolean)

    fun getIsFirstLaunch(): Boolean

    fun setIsFirstLaunch()

    fun setSessionToken(token: String)

    fun getSessionToken(): String

}