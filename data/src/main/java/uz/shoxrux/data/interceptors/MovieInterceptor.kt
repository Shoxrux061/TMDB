package uz.shoxrux.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import uz.shoxrux.core.providers.LocaleProvider
import uz.shoxrux.data.BuildConfig
import javax.inject.Inject

class MovieInterceptor @Inject constructor(
    private val localeProvider: LocaleProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url


        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .addQueryParameter("language", localeProvider.getLanguage())
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
