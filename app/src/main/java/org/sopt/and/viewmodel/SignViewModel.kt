package org.sopt.and.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignViewModel : ViewModel() {

    // 로그인 및 회원가입 관련 변수
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    // 오류 메시지
    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    object Constants {
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 20
        const val PASSWORD_CRITERIA_COUNT = 3
    }

    object RegexConstants {
        val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        val LOWER_CASE_REGEX = Regex("[a-z]")
        val UPPER_CASE_REGEX = Regex("[A-Z]")
        val DIGIT_REGEX = Regex("[0-9]")
        val SPECIAL_REGEX = Regex("[!@#\$%^&*(),.?\\\":{}|<>]")
    }

    /**
     * 이메일과 비밀번호 유효성 검사
     */
    fun validateSignInOrUp(): Boolean {
        return isEmailValid(email) && isPasswordValid(password)
    }


    private fun isEmailValid(email: String): Boolean {
        emailError = if (email.isEmpty()) {
            "이메일을 입력하세요."
        } else if (!RegexConstants.EMAIL_REGEX.matches(email)) {
            "이메일 형식이 올바르지 않습니다."
        } else {
            ""
        }
        return emailError.isEmpty()
    }


    private fun isPasswordValid(password: String): Boolean {
        if (password.isEmpty()) {
            passwordError = "비밀번호를 입력하세요."
            return false
        }

        if (password.length !in Constants.MIN_PASSWORD_LENGTH..Constants.MAX_PASSWORD_LENGTH) {
            passwordError = "비밀번호는 ${Constants.MIN_PASSWORD_LENGTH}-${Constants.MAX_PASSWORD_LENGTH}자여야 합니다."
            return false
        }

        val hasLowerCase = RegexConstants.LOWER_CASE_REGEX.containsMatchIn(password)
        val hasUpperCase = RegexConstants.UPPER_CASE_REGEX.containsMatchIn(password)
        val hasDigit = RegexConstants.DIGIT_REGEX.containsMatchIn(password)
        val hasSpecialChar = RegexConstants.SPECIAL_REGEX.containsMatchIn(password)

        val criteriaCount = listOf(hasLowerCase, hasUpperCase, hasDigit, hasSpecialChar).count { it }

        passwordError = if (criteriaCount >= Constants.PASSWORD_CRITERIA_COUNT) {
            ""
        } else {
            "비밀번호는 영문 대소문자, 숫자, 특수문자 중 3가지 이상을 포함해야 합니다."
        }
        return passwordError.isEmpty()
    }

    /**
     * 로그인 시 이메일과 비밀번호 일치 여부 확인
     */


    var emailSignIn by mutableStateOf("")
    var passwordSignIn by mutableStateOf("")
    var savedEmail: String = "user@example.com" // Mock email
    var savedPassword: String = "password123"    // Mock password

    fun validateSignIn(): Boolean {
        return emailSignIn == savedEmail && passwordSignIn == savedPassword
    }

    /**
     * 이메일과 비밀번호 설정
     */
    fun setEmailAndPassword(email: String, password: String) {
        this.email = email
        this.password = password
    }
}