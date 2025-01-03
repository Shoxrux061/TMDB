package uz.isystem.data.di

import android.content.Context
import android.content.SharedPreferences
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
import uz.isystem.data.cache.LocaleCacheImpl
import uz.isystem.data.network.AuthService
import uz.isystem.data.network.DetailService
import uz.isystem.data.network.MovieService
import uz.isystem.data.network.PeopleDetailService
import uz.isystem.data.network.PeopleService
import uz.isystem.data.network.SearchService
import uz.isystem.data.network.SeriesService
import uz.isystem.data.repository.AuthRepositoryImpl
import uz.isystem.data.repository.DetailRepositoryImpl
import uz.isystem.data.repository.MovieListRepositoryImpl
import uz.isystem.data.repository.PeopleDetailsRepositoryImpl
import uz.isystem.data.repository.PeopleListRepositoryImpl
import uz.isystem.data.repository.SearchRepositoryImpl
import uz.isystem.data.repository.SeriesRepositoryImpl
import uz.isystem.domain.cache.LocaleCache
import uz.isystem.domain.models.auth.CreateTokenResponse
import uz.isystem.domain.models.content.images.ImagesData
import uz.isystem.domain.models.movie.movie_detail.DetailResponse
import uz.isystem.domain.models.movie.movie_detail.TrailerResponse
import uz.isystem.domain.models.movie.movie_detail.crew_details.PeopleDetailResponse
import uz.isystem.domain.models.movie.movie_detail.rec.RecommResponse
import uz.isystem.domain.models.movie.movie_detail.similar.SimilarResponse
import uz.isystem.domain.models.movie.movie_list.MovieListResponse
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.domain.models.people.details.credits.movie.MovieCreditsResponse
import uz.isystem.domain.models.people.details.credits.tv.TvCreditsResponse
import uz.isystem.domain.models.people.details.ids.ExternalIdsResponse
import uz.isystem.domain.models.people.details.images.PeopleImagesResponse
import uz.isystem.domain.models.people.people_list.PeopleListResponse
import uz.isystem.domain.models.series.series_details.SeriesDetailResponse
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse
import uz.isystem.domain.repository.auth.AuthRepository
import uz.isystem.domain.repository.movie.DetailRepository
import uz.isystem.domain.repository.movie.MovieListRepository
import uz.isystem.domain.repository.people.PeopleListRepository
import uz.isystem.domain.repository.people.details.PeopleDetailsRepository
import uz.isystem.domain.repository.search.SearchRepository
import uz.isystem.domain.repository.series.SeriesRepository
import uz.isystem.utills.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideAuthRepository(service: AuthService): AuthRepository<CreateTokenResponse?> {
        return AuthRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @[Provides Singleton]
    fun provideLocaleCache(sharedPreferences: SharedPreferences): LocaleCache {
        return LocaleCacheImpl(sharedPreferences)
    }

    @[Provides Singleton]
    fun provideOfferRepository(service: MovieService): MovieListRepository<MovieListResponse?> {
        return MovieListRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideSearchRepository(service: SearchService): SearchRepository<MovieListResponse?, SeriesResponse?, PeopleListResponse?> {
        return SearchRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun providePDetailsRepository(service: PeopleDetailService): PeopleDetailsRepository<PeopleDetailsResponse?, PeopleImagesResponse?, MovieCreditsResponse?, TvCreditsResponse?, ExternalIdsResponse?> {
        return PeopleDetailsRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideRepository(service: SeriesService): SeriesRepository<SeriesResponse?> {
        return SeriesRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun providePeopleListRepository(service: PeopleService): PeopleListRepository<PeopleListResponse?> {
        return PeopleListRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideDetailRepository(service: DetailService): DetailRepository<DetailResponse?, TrailerResponse?, PeopleDetailResponse?, RecommResponse?, SimilarResponse?, SeriesDetailResponse?, ImagesData?> {
        return DetailRepositoryImpl(service)

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