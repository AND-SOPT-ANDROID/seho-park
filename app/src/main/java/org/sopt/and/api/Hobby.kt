package org.sopt.and.api

import org.sopt.and.dto.ResponseMyHobbyData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HobbyService {
    @GET("/user/my-hobby")
    suspend fun getHobby(
        @Header("token") token: String?
    ): Response<ResponseMyHobbyData>
}