package uz.shoxrux.data.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import uz.shoxrux.core.providers.LocalCacheProvider
import uz.shoxrux.data.BuildConfig
import javax.inject.Inject

class MovieInterceptor @Inject constructor(
    private val localCache: LocalCacheProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val language = runBlocking { localCache.getLanguage() }

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .addQueryParameter("language", language)
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
