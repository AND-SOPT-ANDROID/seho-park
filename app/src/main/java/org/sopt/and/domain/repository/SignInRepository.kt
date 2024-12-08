package org.sopt.and.domain.repository

import org.sopt.and.data.datasource.SignInDataSource
import org.sopt.and.data.repositoryimpl.SignInRepositoryImpl
import org.sopt.and.data.service.ServicePool
import org.sopt.and.domain.model.SignInInformationEntity
import org.sopt.and.domain.model.SignInResponseEntity

interface SignInRepository {
    suspend fun signIn(request: SignInInformationEntity): Result<SignInResponseEntity>

    companion object {
        fun create(): SignInRepositoryImpl {
            return SignInRepositoryImpl(
                SignInDataSource(
                    ServicePool.userService
                )
            )
        }
    }
}