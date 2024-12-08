package org.sopt.and.domain.repository

import org.sopt.and.data.datasource.SignUpDataSource
import org.sopt.and.data.repositoryimpl.SignUpRepositoryImpl
import org.sopt.and.data.service.ServicePool
import org.sopt.and.domain.model.SignUpInformationEntity
import org.sopt.and.domain.model.SignUpResponseEntity

interface SignUpRepository {
    suspend fun signUp(request: SignUpInformationEntity): Result<SignUpResponseEntity>

    companion object {
        fun create(): SignUpRepositoryImpl {
            return SignUpRepositoryImpl(
                signUpDataSource = SignUpDataSource(
                    userService = ServicePool.userService
                )
            )
        }
    }
}