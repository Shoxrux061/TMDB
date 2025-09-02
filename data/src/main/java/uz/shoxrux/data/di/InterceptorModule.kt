package uz.shoxrux.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.shoxrux.core.providers.LocalCacheProvider
import uz.shoxrux.data.interceptors.MovieInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @[Provides Singleton]
    suspend fun provideMovieInterceptor(
        localCache: LocalCacheProvider
    ): MovieInterceptor {
        return MovieInterceptor(localCache)
    }


}