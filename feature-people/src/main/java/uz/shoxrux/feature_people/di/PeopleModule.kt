package uz.shoxrux.feature_people.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.shoxrux.feature_people.data.repository.PeopleListRepositoryImpl
import uz.shoxrux.feature_people.data.service.PeopleService
import uz.shoxrux.feature_people.domain.repository.PeopleListRepository
import uz.shoxrux.feature_people.domain.use_case.GetPopularPeopleUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PeopleModule {

    @Provides
    @Singleton
    fun providePeopleService(retrofit: Retrofit): PeopleService {
        return retrofit.create(PeopleService::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleRepository(service: PeopleService): PeopleListRepository {
        return PeopleListRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun providePeopleUseCase(repository: PeopleListRepository): GetPopularPeopleUseCase {
        return GetPopularPeopleUseCase(repository)
    }

}