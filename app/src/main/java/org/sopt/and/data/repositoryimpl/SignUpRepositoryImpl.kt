package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.SignUpDataSource
import org.sopt.and.data.mapper.Mapper
import org.sopt.and.domain.model.SignUpInformationEntity
import org.sopt.and.domain.model.SignUpResponseEntity
import org.sopt.and.domain.usecase.SignUpRepository

class SignUpRepositoryImpl(
    private val signUpDataSource: SignUpDataSource
) : SignUpRepository {
    override suspend fun signUp(request: SignUpInformationEntity): Result<SignUpResponseEntity> =
        runCatching {
            Mapper.toSignUpResponseEntity(
                signUpDataSource.signUp(
                    Mapper.toSignUpRequestDto(
                        request
                    )
                )
            )!!
        }
}