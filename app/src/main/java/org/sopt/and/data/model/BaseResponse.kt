package org.sopt.and.data.model

import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.BaseResult

@Serializable
sealed class BaseResponse<out T> {
    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Failure(
        val statusCode: Int?,
        val errorCode: String?,
        val message: String
    ) : BaseResponse<Nothing>()
}
@Serializable
data class ErrorResponse(
    val code: String
)

fun <T> BaseResponse<T>.toBaseResult(): BaseResult<T> {
    return when (this) {
        is BaseResponse.Success -> BaseResult.Success(this.data)
        is BaseResponse.Failure -> BaseResult.Error(this.message, this.errorCode)
    }
}