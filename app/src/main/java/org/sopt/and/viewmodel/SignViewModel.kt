package org.sopt.and.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.and.api.LoginService
import org.sopt.and.api.UserRegistrationService
import org.sopt.and.dto.RequestLoginData
import org.sopt.and.dto.RequestUserRegistrationData
import org.sopt.and.dto.ResponseUserRegistration
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    application: Application,
    private val userRegistrationService: UserRegistrationService, // Hilt를 통한 주입
    private val loginService: LoginService // Hilt를 통한 주입
) : AndroidViewModel(application) {

    private val preferences: SharedPreferences by lazy {
        application.getSharedPreferences("user_prefs", Application.MODE_PRIVATE)
    }

    var email by mutableStateOf(TextFieldValue("")) // TextFieldValue 사용
    var password by mutableStateOf(TextFieldValue("")) // TextFieldValue 사용
    var isPasswordVisible by mutableStateOf(false)

    private var emailError by mutableStateOf("")
    private var passwordError by mutableStateOf("")

    /** 회원가입 API 요청 */
    fun performSignUp(
        username: String,
        password: String,
        hobby: String,
        onSuccess: (ResponseUserRegistration) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = RequestUserRegistrationData(username, password, hobby)
                val response = userRegistrationService.postUserRegistration(request) // 주입된 서비스 사용
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onFailure("서버 응답이 비어있습니다.")
                } else {
                    onFailure("회원가입 실패: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                onFailure("에러 발생: ${e.localizedMessage}")
            }
        }
    }

    /** 로그인 API 요청 */
    fun performLogin(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = RequestLoginData(
                    userName = email.text,
                    password = password.text
                )
                Log.d("SignViewModel", "로그인 요청 데이터: $request") // 요청 데이터 로그

                val response = loginService.postLogin(request) // Hilt로 주입된 서비스 사용
                if (response.isSuccessful) {
                    response.body()?.let {
                        val token = it.result.token
                        Log.d("SignViewModel", "로그인 성공, 토큰: $token") // 성공 응답 로그
                        preferences.edit().putString("auth_token", token).apply()
                        onSuccess()
                    } ?: onFailure("서버 응답이 비어있습니다.")
                } else {
                    Log.e(
                        "SignViewModel",
                        "로그인 실패: ${response.code()} - ${response.message()} - ${response.errorBody()?.string()}"
                    ) // 실패 로그
                    onFailure("로그인 실패: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("SignViewModel", "로그인 요청 중 에러 발생: ${e.localizedMessage}", e) // 예외 로그
                onFailure("에러 발생: ${e.localizedMessage}")
            }
        }
    }

    /** Validate inputs for sign-up */
    fun validateSignUpInputs(username: String, password: String, hobby: String): Boolean {
        return username.length <= 8 && password.length <= 8 && hobby.length <= 8
    }

    /** Validate email and password during sign-in */
    fun validateSignInInputs(): Boolean {
        return email.text.isNotEmpty() && password.text.isNotEmpty()
    }

    /** Checks if the email meets the required format */
    private fun isEmailValid(): Boolean {
        emailError = when {
            email.text.isEmpty() -> "이메일을 입력하세요."
            !Constants.EMAIL_REGEX.matches(email.text) -> "이메일 형식이 올바르지 않습니다."
            else -> ""
        }
        return emailError.isEmpty()
    }

    /** Checks if the password meets length and complexity requirements */
    private fun isPasswordValid(): Boolean {
        passwordError = when {
            password.text.isEmpty() -> "비밀번호를 입력하세요."
            password.text.length !in Constants.MIN_PASSWORD_LENGTH..Constants.MAX_PASSWORD_LENGTH ->
                "비밀번호는 ${Constants.MIN_PASSWORD_LENGTH}-${Constants.MAX_PASSWORD_LENGTH}자여야 합니다."
            !isPasswordComplexEnough(password.text) ->
                "비밀번호는 영문 대소문자, 숫자, 특수문자 중 3가지 이상을 포함해야 합니다."
            else -> ""
        }
        return passwordError.isEmpty()
    }

    companion object Constants {
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 20
        const val PASSWORD_CRITERIA_COUNT = 3
        val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        val LOWER_CASE_REGEX = Regex("[a-z]")
        val UPPER_CASE_REGEX = Regex("[A-Z]")
        val DIGIT_REGEX = Regex("[0-9]")
        val SPECIAL_REGEX = Regex("[!@#\$%^&*(),.?\\\":{}|<>]")
    }

    private fun isPasswordComplexEnough(password: String): Boolean {
        val criteriaCount = listOf(
            Constants.LOWER_CASE_REGEX.containsMatchIn(password),
            Constants.UPPER_CASE_REGEX.containsMatchIn(password),
            Constants.DIGIT_REGEX.containsMatchIn(password),
            Constants.SPECIAL_REGEX.containsMatchIn(password)
        ).count { it }
        return criteriaCount >= Constants.PASSWORD_CRITERIA_COUNT
    }
}