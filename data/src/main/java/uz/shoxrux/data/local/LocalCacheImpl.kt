package uz.shoxrux.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import uz.shoxrux.core.providers.LocalCacheProvider
import javax.inject.Inject

class LocalCacheImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalCacheProvider {

    private val languageKey = "LANGUAGE_KEY"
    private val isDarkKey = "IS_DARK_KEY"
    private val isFirstLaunchKey = "IS_FIRST_LAUNCH_KEY"
    private val sessionTokenKey = "SESSION_TOKEN_KEY"

    override fun getLanguage(): String {
        return sharedPreferences.getString(languageKey, "en") ?: "en"
    }

    override fun isDark(): Boolean {
        return sharedPreferences.getBoolean(isDarkKey, false)
    }

    override fun setLanguage(lang: String) {
        sharedPreferences.edit { putString(languageKey, lang) }
    }

    override fun setIsDark(isDark: Boolean) {
        sharedPreferences.edit { putBoolean(isDarkKey, isDark) }
    }

    override fun getIsFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(isFirstLaunchKey, true)
    }

    override fun setIsFirstLaunch() {
        sharedPreferences.edit { putBoolean(isFirstLaunchKey, false) }
    }

    override fun setSessionToken(token: String) {
        sharedPreferences.edit { putString(sessionTokenKey, token) }
    }

    override fun getSessionToken(): String? {
        return sharedPreferences.getString(sessionTokenKey, null)
    }

}