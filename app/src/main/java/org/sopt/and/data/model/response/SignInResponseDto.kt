package org.sopt.and.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    val result: SignInResponseResultDto? = null,
    val code: String? = null
)

@Serializable
data class SignInResponseResultDto(
    val token: String
)
