package uz.shoxrux.di

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uz.shoxrux.core.providers.LocaleProvider
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {

    @[Provides Singleton]
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @[Provides Singleton]
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @[Provides Singleton]
    fun provideOkHttp(
        chuckerInterceptor: ChuckerInterceptor,
        apiKeyInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()

        Log.d("TAGResponse", "provideOkHttp: ${client.interceptors}")

        return client
    }
    @[Provides Singleton]
    fun provideChuckerInterceptor(
        collector: ChuckerCollector,
        @ApplicationContext context: Context
    ): ChuckerInterceptor {

        return ChuckerInterceptor.Builder(context)
            .collector(collector)
            .maxContentLength(250_000L)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()
    }

    @[Provides Singleton]
    fun provideChuckerCollector(@ApplicationContext context: Context): ChuckerCollector {
        return ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
    }

    @[Provides Singleton]
    fun provideMovieInterceptor(
        localeProvider: LocaleProvider
    ): Interceptor {
        return Interceptor { chain ->

            val original = chain.request()

            val newUrl = original.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .addQueryParameter("language", localeProvider.getLanguage())
                .build()

            val newRequest = original.newBuilder()
                .url(newUrl)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()

            Log.d(
                "MovieInterceptor",
                "language=${localeProvider.getLanguage()} api_key=${BuildConfig.TMDB_API_KEY}"
            )
            Log.d("MovieInterceptor", "original=${original.url}")
            Log.d("MovieInterceptor", "new=$newUrl")

            chain.proceed(newRequest)
        }
    }

}