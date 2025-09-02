package uz.shoxrux.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import uz.shoxrux.core.providers.LocalCacheProvider
import javax.inject.Inject

class LocalCacheImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalCacheProvider {


    private object Keys {
        val LANGUAGE = stringPreferencesKey("LANGUAGE_KEY")
        val IS_DARK = booleanPreferencesKey("IS_DARK_KEY")
        val IS_FIRST_LAUNCH = booleanPreferencesKey("IS_FIRST_LAUNCH_KEY")
        val SESSION_TOKEN = stringPreferencesKey("SESSION_TOKEN_KEY")
    }

    override suspend fun getLanguage(): String =
        dataStore.data.map { prefs -> prefs[Keys.LANGUAGE] ?: "en" }.first()

    override suspend fun setLanguage(lang: String) {
        dataStore.edit { prefs -> prefs[Keys.LANGUAGE] = lang }
    }

    override suspend fun isDark(): Boolean =
        dataStore.data.map { prefs -> prefs[Keys.IS_DARK] ?: false }.first()

    override suspend fun setIsDark(isDark: Boolean) {
        dataStore.edit { prefs -> prefs[Keys.IS_DARK] = isDark }
    }

    override suspend fun getIsFirstLaunch(): Boolean =
        dataStore.data.map { prefs -> prefs[Keys.IS_FIRST_LAUNCH] ?: true }.first()

    override suspend fun setIsFirstLaunch() {
        dataStore.edit { prefs -> prefs[Keys.IS_FIRST_LAUNCH] = false }
    }

    override suspend fun getSessionToken(): String? =
        dataStore.data.map { prefs -> prefs[Keys.SESSION_TOKEN] }.first()

    override suspend fun setSessionToken(token: String) {
        dataStore.edit { prefs -> prefs[Keys.SESSION_TOKEN] = token }
    }

}