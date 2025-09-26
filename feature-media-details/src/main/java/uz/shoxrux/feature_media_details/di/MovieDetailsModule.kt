package uz.shoxrux.feature_media_details.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.shoxrux.feature_media_details.data.repository.MediaDetailsRepositoryImpl
import uz.shoxrux.feature_media_details.data.service.MediaDetailsService
import uz.shoxrux.feature_media_details.domain.repository.MediaDetailsRepository
import uz.shoxrux.feature_media_details.domain.use_case.GetMovieDetailsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @[Provides Singleton]
    fun provideMovieDetailsService(
        retrofit: Retrofit
    ): MediaDetailsService {
        return retrofit.create(MediaDetailsService::class.java)
    }

    @[Provides Singleton]
    fun provideMovieDetailsRepository(service: MediaDetailsService): MediaDetailsRepository {
        return MediaDetailsRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideMovieDetailsUseCase(repository: MediaDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }

}