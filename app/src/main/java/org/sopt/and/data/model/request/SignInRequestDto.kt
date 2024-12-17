package org.sopt.and.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    val username: String,
    val password: String
)
