package org.sopt.and.data.datasource

import org.sopt.and.data.model.request.SignInRequestDto
import org.sopt.and.data.model.response.SignInResponseDto
import org.sopt.and.data.service.UserService
import retrofit2.Response

class SignInDataSource(
    private val userService: UserService
) {
    suspend fun signIn(request: SignInRequestDto): Response<SignInResponseDto> =
        userService.signIn(request = request)
}