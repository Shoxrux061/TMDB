package uz.shoxrux.tmdb.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.shoxrux.core.providers.LocaleProvider
import uz.shoxrux.tmdb.data.LocaleProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidersModule {

    @Provides
    @Singleton
    fun provideLocaleProvider(@ApplicationContext context: Context): LocaleProvider {
        return LocaleProviderImpl(context)
    }

}