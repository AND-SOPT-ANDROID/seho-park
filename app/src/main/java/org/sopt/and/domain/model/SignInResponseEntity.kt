package org.sopt.and.domain.model

data class SignInResponseEntity(
    val token: String? = null,
    val status: Int? = null,
    val code: String? = null
)
