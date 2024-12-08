package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.SignUpInformationEntity
import org.sopt.and.domain.model.SignUpResponseEntity
import org.sopt.and.domain.repository.SignUpRepository

class SignUpUseCase(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(request: SignUpInformationEntity): Result<SignUpResponseEntity> =
        signUpRepository.signUp(request = request)
}