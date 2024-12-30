package uz.isystem.domain.cache

interface LocaleCache {

    fun saveSessionId(sessionId: String)

    fun getSessionId(): String

}