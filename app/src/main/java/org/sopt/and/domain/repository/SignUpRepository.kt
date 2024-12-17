package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.BaseResult
import org.sopt.and.domain.entity.UserData
import org.sopt.and.domain.entity.UserRegisterResult


interface SignUpRepository {
    suspend fun registerUser(user : UserData): BaseResult<UserRegisterResult>
}