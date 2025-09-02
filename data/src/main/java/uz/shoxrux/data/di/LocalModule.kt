package uz.shoxrux.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.shoxrux.core.providers.LocalCacheProvider
import uz.shoxrux.data.local.LocalCacheImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.dataStoreFile("app_prefs") }
        )
    }

    @Provides
    @Singleton
    fun provideLocalCache(
        dataStore: DataStore<Preferences>
    ): LocalCacheProvider {
        return LocalCacheImpl(dataStore)
    }

}