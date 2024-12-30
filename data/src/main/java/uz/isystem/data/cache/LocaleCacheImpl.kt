package uz.isystem.data.cache

import android.content.SharedPreferences
import uz.isystem.domain.cache.LocaleCache
import javax.inject.Inject

class LocaleCacheImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocaleCache {

    private val keySessionId = "KEY_SESSION_ID"

    override fun saveSessionId(sessionId: String) {
        sharedPreferences.edit().putString(keySessionId, sessionId).apply()
    }

    override fun getSessionId(): String {
        return sharedPreferences.getString(keySessionId, "") ?: ""
    }
}