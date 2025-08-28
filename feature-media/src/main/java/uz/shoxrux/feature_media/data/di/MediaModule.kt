package uz.shoxrux.feature_media.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.shoxrux.feature_media.data.repository.MediaRepositoryImpl
import uz.shoxrux.feature_media.data.services.media_servcie.MediaService
import uz.shoxrux.feature_media.domain.repository.MediaRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaModule {

    @[Provides Singleton]
    fun provideMediaService(retrofit: Retrofit): MediaService {
        return retrofit.create(MediaService::class.java)
    }

    @[Provides Singleton]
    fun provideMediaRepository(service: MediaService): MediaRepository {
        return MediaRepositoryImpl(service)
    }

}