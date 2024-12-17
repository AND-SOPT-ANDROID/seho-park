package org.sopt.and.domain.repository


import org.sopt.and.domain.entity.BaseResult
import org.sopt.and.domain.entity.UserLoginResult


interface SignInRepository {
    suspend fun loginUser(user: org.sopt.and.domain.entity.UserData): BaseResult<UserLoginResult>
}