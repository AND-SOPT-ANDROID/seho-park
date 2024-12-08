package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.datasource.SignInDataSource
import org.sopt.and.data.mapper.Mapper
import org.sopt.and.domain.model.SignInInformationEntity
import org.sopt.and.domain.model.SignInResponseEntity
import org.sopt.and.domain.usecase.SignInRepository

class SignInRepositoryImpl(
    private val signInDataSource: SignInDataSource
) : SignInRepository {
    override suspend fun signIn(request: SignInInformationEntity): Result<SignInResponseEntity> =
        runCatching {
            Mapper.toSignInResponseEntity(
                signInDataSource.signIn(
                    Mapper.toSignInRequestDto(
                        request
                    )
                )
            )!!
        }
}