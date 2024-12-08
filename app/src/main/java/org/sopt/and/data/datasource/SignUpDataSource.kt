package org.sopt.and.data.datasource

import org.sopt.and.data.model.request.SignUpRequestDto
import org.sopt.and.data.model.response.SignUpResponseDto
import org.sopt.and.data.service.UserService
import retrofit2.Response

class SignUpDataSource(
    private val userService: UserService
) {
    suspend fun signUp(request: SignUpRequestDto): Response<SignUpResponseDto> =
        userService.signUp(request = request)
}