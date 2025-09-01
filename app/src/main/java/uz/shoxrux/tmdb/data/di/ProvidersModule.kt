package uz.shoxrux.tmdb.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.shoxrux.core.providers.LocaleProvider
import uz.shoxrux.tmdb.data.LocaleProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidersModule {

    @Provides
    @Singleton
    fun provideLocaleProvider(localeProviderImpl: LocaleProviderImpl): LocaleProvider {
        return localeProviderImpl
    }

}