package org.sopt.and.data.api

import org.sopt.and.data.model.request.SignInRequestDto
import org.sopt.and.data.model.request.SignUpRequestDto
import org.sopt.and.data.model.response.MyHobbyResponseDto
import org.sopt.and.data.model.response.SignInResponseDto
import org.sopt.and.data.model.response.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun registerUser(
        @Body request: SignUpRequestDto
    ): Response<SignUpResponseDto>

    @POST("/login")
    suspend fun loginUser(
        @Body request: SignInRequestDto
    ): Response<SignInResponseDto>

    @GET("/user/my-hobby")
    suspend fun getMyHobby(
        @Header("token") token: String
    ): Response<MyHobbyResponseDto>
}