package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.SignInInformationEntity
import org.sopt.and.domain.model.SignInResponseEntity
import org.sopt.and.domain.repository.SignInRepository

class SignInUseCase(
    private val signInRepository: SignInRepository
) {
    suspend operator fun invoke(request: SignInInformationEntity): Result<SignInResponseEntity> =
        signInRepository.signIn(request = request)
}