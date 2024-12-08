package org.sopt.and.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/* 유저 등록 */
@Serializable
data class RequestUserRegistrationData(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String,
    @SerialName("hobby")
    val hobby: String
)

@Serializable
data class ResponseUserRegistration(
    @SerialName("result")
    val result: ResultUserNo
)

@Serializable
data class ResultUserNo(
    @SerialName("no")
    val no: Int
)

/* 로그인 */
@Serializable
data class RequestLoginData(
    @SerialName("username")
    val userName: String,
    @SerialName("password")
    val password: String
)

@Serializable
data class ResponseLogin(
    @SerialName("result")
    val result: ResultToken
)

@Serializable
data class ResultToken(
    @SerialName("token")
    val token: String
)