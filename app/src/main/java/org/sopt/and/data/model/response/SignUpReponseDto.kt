package org.sopt.and.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    val result: SignUpResponseResultDto? = null,
    val code: String? = null
)

@Serializable
data class SignUpResponseResultDto(
    val no: Int
)