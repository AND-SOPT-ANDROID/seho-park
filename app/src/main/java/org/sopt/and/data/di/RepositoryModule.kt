package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.api.UserService
import org.sopt.and.data.datasource.MyHobbyDataSource
import org.sopt.and.data.datasource.SignUpDataSource
import org.sopt.and.data.repositoryimpl.MyHobbyRepositoryImpl
import org.sopt.and.data.repositoryimpl.SignInRepositoryImpl
import org.sopt.and.data.repositoryimpl.SignUpRepositoryImpl
import org.sopt.and.domain.repository.MyHobbyRepository
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.domain.repository.SignInRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRegisterRepository(userService: UserService): SignUpRepository {
        return SignUpRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideUserLoginRepository(userService: UserService): SignInRepository {
        return SignInRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideGetMyHobbyRepository(userService: MyHobbyDataSource): MyHobbyRepository {
        return MyHobbyRepositoryImpl(userService)
    }
}