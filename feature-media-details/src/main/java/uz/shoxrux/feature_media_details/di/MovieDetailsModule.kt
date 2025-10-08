package uz.shoxrux.feature_media_details.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.shoxrux.feature_media_details.data.repository.MediaDetailsRepositoryImpl
import uz.shoxrux.feature_media_details.data.service.MediaDetailsService
import uz.shoxrux.feature_media_details.domain.repository.MediaDetailsRepository
import uz.shoxrux.feature_media_details.domain.use_case.movie.CombineMovieScreenUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieCreditsUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieDetailsUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieImagesUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieRecommendationUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieReviewUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieSimilarUseCase
import uz.shoxrux.feature_media_details.domain.use_case.movie.GetMovieVideosUseCase
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

    @[Provides Singleton]
    fun provideMovieCreditsUseCase(repository: MediaDetailsRepository): GetMovieCreditsUseCase {
        return GetMovieCreditsUseCase(repository)
    }

    @[Provides Singleton]
    fun provideMovieReviewUseCase(repository: MediaDetailsRepository): GetMovieReviewUseCase {
        return GetMovieReviewUseCase(repository)
    }

    @[Provides Singleton]
    fun provideMovieImagesUseCase(repository: MediaDetailsRepository): GetMovieImagesUseCase {
        return GetMovieImagesUseCase(repository)
    }

    @[Provides Singleton]
    fun provideMovieSimilarUseCase(repository: MediaDetailsRepository): GetMovieRecommendationUseCase {
        return GetMovieRecommendationUseCase(repository)
    }

    @[Provides Singleton]
    fun provideMovieVideosUseCase(repository: MediaDetailsRepository): GetMovieVideosUseCase {
        return GetMovieVideosUseCase(repository)
    }

    @[Provides Singleton]
    fun provideCombineMovieScreenUseCase(
        getMovieDetailsUseCase: GetMovieDetailsUseCase,
        getMovieCreditsUseCase: GetMovieCreditsUseCase,
        getMovieReviewUseCase: GetMovieReviewUseCase,
        getMovieRecommendationUseCase: GetMovieRecommendationUseCase,
        getMovieImagesUseCase: GetMovieImagesUseCase,
        getMovieVideosUseCase: GetMovieVideosUseCase,
        getMovieSimilarUseCase: GetMovieSimilarUseCase
    ): CombineMovieScreenUseCase {

        return CombineMovieScreenUseCase(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            getMovieCreditsUseCase = getMovieCreditsUseCase,
            getMovieReviewsUseCase = getMovieReviewUseCase,
            getMovieRecommendationUseCase = getMovieRecommendationUseCase,
            getMovieImagesUseCase = getMovieImagesUseCase,
            getMovieVideosUseCase = getMovieVideosUseCase,
            getMovieSimilarUseCase = getMovieSimilarUseCase
        )
    }
}