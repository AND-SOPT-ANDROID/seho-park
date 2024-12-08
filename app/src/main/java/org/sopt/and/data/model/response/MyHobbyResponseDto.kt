package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyHobbyResponseDto(
    val result: MyHobbyResponseResultDto? = null,
    val code: String? = null
)

@Serializable
data class MyHobbyResponseResultDto(
    @SerialName("hobby")
    val myHobby: String
)