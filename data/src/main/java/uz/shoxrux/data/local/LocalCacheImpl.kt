package uz.shoxrux.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
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

    override fun getLanguage(): Flow<String> =
        dataStore.data.map { it[Keys.LANGUAGE] ?: "en" }

    override fun isDark(): Flow<Boolean> =
        dataStore.data.map { it[Keys.IS_DARK] ?: false }

    override fun getIsFirstLaunch(): Flow<Boolean> =
        dataStore.data.map { it[Keys.IS_FIRST_LAUNCH] ?: true }

    override fun getSessionToken(): Flow<String?> =
        dataStore.data.map { it[Keys.SESSION_TOKEN] }

    override suspend fun setLanguage(lang: String) {
        dataStore.edit { it[Keys.LANGUAGE] = lang }
    }

    override suspend fun setIsDark(isDark: Boolean) {
        dataStore.edit { it[Keys.IS_DARK] = isDark }
    }

    override suspend fun setIsFirstLaunch() {
        dataStore.edit { it[Keys.IS_FIRST_LAUNCH] = false }
    }

    override suspend fun setSessionToken(token: String) {
        dataStore.edit { it[Keys.SESSION_TOKEN] = token }
    }
}