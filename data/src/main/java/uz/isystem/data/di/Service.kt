package uz.isystem.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.isystem.data.network.AuthService
import uz.isystem.data.network.DetailService
import uz.isystem.data.network.MovieService
import uz.isystem.data.network.PeopleDetailService
import uz.isystem.data.network.PeopleService
import uz.isystem.data.network.SearchService
import uz.isystem.data.network.SeriesService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Service {

    @[Provides Singleton]
    fun provideHomeService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @[Provides Singleton]
    fun provideDetailService(retrofit: Retrofit): DetailService {
        return retrofit.create(DetailService::class.java)
    }

    @[Provides Singleton]
    fun provideSeriesService(retrofit: Retrofit): SeriesService {
        return retrofit.create(SeriesService::class.java)
    }

    @[Provides Singleton]
    fun providePeopleService(retrofit: Retrofit): PeopleService {
        return retrofit.create(PeopleService::class.java)
    }

    @[Provides Singleton]
    fun providePDetailsService(retrofit: Retrofit): PeopleDetailService {
        return retrofit.create(PeopleDetailService::class.java)
    }

    @[Provides Singleton]
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @[Provides Singleton]
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}