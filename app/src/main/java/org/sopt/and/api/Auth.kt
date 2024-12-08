package org.sopt.and.api

import org.sopt.and.dto.RequestLoginData
import org.sopt.and.dto.RequestUserRegistrationData
import org.sopt.and.dto.ResponseLogin
import org.sopt.and.dto.ResponseUserRegistration
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserRegistrationService {
    @POST("/user")
    suspend fun postUserRegistration(
        @Body userRequest: RequestUserRegistrationData
    ): Response<ResponseUserRegistration>
}

interface LoginService {
    @POST("/login")
    suspend fun postLogin(
        @Body loginRequeset: RequestLoginData
    ): Response<ResponseLogin>
}

