package uz.isystem.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.isystem.data.network.MovieService
import uz.isystem.data.repository.MovieListRepositoryImpl
import uz.isystem.domain.models.MovieListResponse
import uz.isystem.domain.repository.MovieListRepository
import uz.isystem.utills.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideOfferRepository(service: MovieService): MovieListRepository<MovieListResponse?> {
        return MovieListRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @[Provides Singleton]
    fun provideOkHttp(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(chuckerInterceptor)
            .build()
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
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @[Provides Singleton]
    fun provideInterception(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val builder: Request.Builder = request.newBuilder()
            builder
                .header("Connection", "close")
                .addHeader("Content-type", "application/json")
                .addHeader("Authorization", Constants.TOKEN)
                .addHeader("api_key", Constants.API_KEY)
            val response = chain.proceed(builder.build())
            response
        }
    }
}