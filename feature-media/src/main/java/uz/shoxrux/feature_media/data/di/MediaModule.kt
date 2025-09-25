package uz.shoxrux.feature_media.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.shoxrux.feature_media.data.repository.MediaRepositoryImpl
import uz.shoxrux.feature_media.data.services.media_servcie.MediaService
import uz.shoxrux.feature_media.domain.repository.MediaRepository
import uz.shoxrux.feature_media.domain.use_case.movies.GetAllMoviesUseCase
import uz.shoxrux.feature_media.domain.use_case.movies.GetMoviesByCategoryUseCase
import uz.shoxrux.feature_media.domain.use_case.series.GetAllSeriesUseCase
import uz.shoxrux.feature_media.domain.use_case.series.GetSeriesByCategoryUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaModule {

    @[Provides Singleton]
    fun provideMediaService(retrofit: Retrofit): MediaService {
        return retrofit.create(MediaService::class.java)
    }

    @[Provides Singleton]
    fun provideMediaRepository(
        service: MediaService
    ): MediaRepository {
        return MediaRepositoryImpl(service)
    }

    @[Provides Singleton]
    fun provideMovieUseCase(
        mediaRepository: MediaRepository
    ): GetMoviesByCategoryUseCase {
        return GetMoviesByCategoryUseCase(mediaRepository)
    }

    @[Provides Singleton]
    fun provideSeriesUseCase(
        mediaRepository: MediaRepository
    ): GetSeriesByCategoryUseCase = GetSeriesByCategoryUseCase(
        mediaRepository
    )

    @[Provides Singleton]
    fun provideAllSeriesUseCase(
        getSeriesByCategoryUseCase: GetSeriesByCategoryUseCase
    ): GetAllSeriesUseCase = GetAllSeriesUseCase(
        getSeriesByCategoryUseCase
    )

    @[Provides Singleton]
    fun provideAllMoviesUseCase(
        getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase
    ): GetAllMoviesUseCase = GetAllMoviesUseCase(
        getMoviesByCategoryUseCase
    )

}