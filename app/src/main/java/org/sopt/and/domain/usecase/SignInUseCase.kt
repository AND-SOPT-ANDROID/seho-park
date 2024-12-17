package org.sopt.and.domain.usecase

import org.sopt.and.domain.entity.BaseResult
import org.sopt.and.domain.entity.UserData
import org.sopt.and.domain.entity.UserLoginResult
import org.sopt.and.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userLoginRepository: SignInRepository
) {
    suspend operator fun invoke(user: UserData): BaseResult<UserLoginResult> {
        return userLoginRepository.loginUser(user)
    }
}