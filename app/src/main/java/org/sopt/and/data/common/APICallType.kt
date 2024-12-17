package org.sopt.and.data.common


object APICallType {
    const val REGISTER_USER = "registerUser"
    const val LOGIN_USER = "loginUser"
    const val GET_MY_HOBBY = "getMyHobby"
}
object ErrorTypeWithMessage {
    const val INVALID_TOKEN = "다시 로그인 해주세요." //토큰이 유효하지 않거나 없음, 다시 로그인 하도록 유도함
}