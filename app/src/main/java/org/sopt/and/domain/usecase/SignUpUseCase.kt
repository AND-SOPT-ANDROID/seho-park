package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.BaseResult
import org.sopt.and.domain.entity.UserData
import org.sopt.and.domain.entity.UserRegisterResult
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRegisterRepository: SignUpRepository
) {
    suspend operator fun invoke(user: UserData): BaseResult<UserRegisterResult> {
        return userRegisterRepository.registerUser(user)
    }
}