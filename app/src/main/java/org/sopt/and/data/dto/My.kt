package org.sopt.and.data.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/* 내 취미 조회 */
@Serializable
data class ResponseMyHobbyData(
    @SerialName("result")
    val result: ResponseMyHobbyDataResult
)

@Serializable
data class ResponseMyHobbyDataResult(
    @SerialName("hobby")
    val hobby: String
)
