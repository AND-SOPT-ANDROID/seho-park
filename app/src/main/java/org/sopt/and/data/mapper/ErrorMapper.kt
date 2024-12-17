package org.sopt.and.data.mapper

import org.sopt.and.data.common.APICallType

object ErrorMapper {
    private val errorMapByApi = mapOf(
        APICallType.REGISTER_USER to mapOf(
            Pair(400, "01") to "잘못된 요청입니다.",
            Pair(400, "01") to "아이디, 비밀번호, 취미를 올바르게 입력해주세요.",
            Pair(404, null) to "잘못된 요청입니다.",
            Pair(409, "00") to "중복된 아이디입니다."
        ),
        APICallType.LOGIN_USER to mapOf(
            Pair(400, "01") to "비밀번호를 올바르게 입력해주세요.",
            Pair(400, "02") to "비밀번호를 올바르게 입력해주세요.",
            Pair(403, "01") to "아이디 혹은 비밀번호가 틀렸습니다.",
            Pair(404, "00") to "잘못된 요청입니다."
        ),
        APICallType.GET_MY_HOBBY to mapOf(
            Pair(401, "00") to "잘못된 요청입니다.",
            Pair(403, "00") to "잘못된 요청입니다.",
            Pair(404, "00") to "잘못된 요청입니다."
        ),

        )
    fun getErrorMessage(apiName: String, statusCode: Int?, errorCode: String?): String {
        val apiErrorMap = errorMapByApi[apiName]
        val message = apiErrorMap?.get(Pair(statusCode, errorCode))

        return message ?: "알 수 없는 에러"
    }
}
