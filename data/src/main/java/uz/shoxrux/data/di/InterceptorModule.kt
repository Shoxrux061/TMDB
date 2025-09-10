package uz.shoxrux.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.shoxrux.core.providers.LocaleProvider
import uz.shoxrux.data.interceptors.MovieInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @[Provides Singleton]
    suspend fun provideMovieInterceptor(
        localeProvider: LocaleProvider,
    ): MovieInterceptor {
        return MovieInterceptor(localeProvider)
    }

}