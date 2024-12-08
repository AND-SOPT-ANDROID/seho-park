package org.sopt.and.data.service

import org.sopt.and.data.model.request.SignInRequestDto
import org.sopt.and.data.model.request.SignUpRequestDto
import org.sopt.and.data.model.response.MyHobbyResponseDto
import org.sopt.and.data.model.response.SignInResponseDto
import org.sopt.and.data.model.response.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    suspend fun signUp(@Body request: SignUpRequestDto): Response<SignUpResponseDto>

    @POST("/login")
    suspend fun signIn(@Body request: SignInRequestDto): Response<SignInResponseDto>

    @GET("/user/my-hobby")
    suspend fun getMyHobby(): MyHobbyResponseDto
}