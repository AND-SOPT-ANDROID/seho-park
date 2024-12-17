package org.sopt.and.data.repositoryimpl

import kotlinx.serialization.json.Json
import org.sopt.and.data.api.UserService
import org.sopt.and.data.common.APICallType
import org.sopt.and.data.mapper.ErrorMapper
import org.sopt.and.data.mapper.Mapper.toRegisterRequestDto
import org.sopt.and.data.model.BaseResponse
import org.sopt.and.data.model.ErrorResponse
import org.sopt.and.data.model.toBaseResult
import org.sopt.and.domain.entity.UserData
import org.sopt.and.domain.entity.UserRegisterResult
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.domain.entity.BaseResult
import retrofit2.HttpException
import javax.inject.Inject


class SignUpRepositoryImpl @Inject constructor(
    private val userService: UserService
) : SignUpRepository {
    override suspend fun registerUser(user: UserData): BaseResult<UserRegisterResult> {
        val apiResult : BaseResponse<UserRegisterResult> = try {
            val response = userService.registerUser(user.toRegisterRequestDto())
            if (response.isSuccessful) {
                response.body()?.result?.let {
                    BaseResponse.Success(UserRegisterResult(it.no))
                } ?: BaseResponse.Failure(null, null, "응답에 실패했습니다.")
            } else {
                val errorCode = response.errorBody()?.string()?.let { errorBodyString ->
                    try {
                        Json.decodeFromString<ErrorResponse>(errorBodyString).code
                    } catch (e: Exception) {
                        null
                    }
                }
                val errorMessage = ErrorMapper.getErrorMessage(
                    APICallType.REGISTER_USER,
                    response.code(),
                    errorCode
                )
                BaseResponse.Failure(response.code(), errorCode, errorMessage)
            }
        } catch (e: HttpException) {
            val errorCode = e.response()?.errorBody()?.string()?.let { errorBodyString ->
                try {
                    Json.decodeFromString<ErrorResponse>(errorBodyString).code
                } catch (e: Exception) {
                    null
                }
            }
            val errorMessage = ErrorMapper.getErrorMessage(
                APICallType.REGISTER_USER,
                e.response()?.code(),
                errorCode
            )
            BaseResponse.Failure(e.code(), errorCode, errorMessage)
        } catch (e: Exception) {
            BaseResponse.Failure(null, null, "네트워크 연결을 확인해주세요.")
        }

        return apiResult.toBaseResult()
    }
}