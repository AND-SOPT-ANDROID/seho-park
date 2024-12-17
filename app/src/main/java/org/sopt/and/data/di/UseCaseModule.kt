package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.domain.repository.MyHobbyRepository
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.domain.repository.SignInRepository
import org.sopt.and.domain.usecase.MyHobbyUseCase
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.domain.usecase.SignUpUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(userRepository: SignUpRepository): SignUpUseCase {
        return SignUpUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUserUseCase(userRepository: SignInRepository): SignInUseCase {
        return SignInUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetMyHobbyUseCase(userRepository: MyHobbyRepository): MyHobbyUseCase {
        return MyHobbyUseCase(userRepository)
    }
}