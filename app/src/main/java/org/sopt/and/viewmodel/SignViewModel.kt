package org.sopt.and.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    object Constants {
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"

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

    fun validateSignInOrUp(): Boolean {
        return isEmailValid(email) && isPasswordValid(password)
    }

    /**
     * 이메일 유효성 검사
     */
    private fun isEmailValid(email: String): Boolean {
        return RegexConstants.EMAIL_REGEX.matches(email)
    }

    /**
     * 비밀번호 유효성 검사
     */
    private fun isPasswordValid(password: String): Boolean {
        if (password.length !in Constants.MIN_PASSWORD_LENGTH..Constants.MAX_PASSWORD_LENGTH) {
            return false
        }

        val hasLowerCase = RegexConstants.LOWER_CASE_REGEX.containsMatchIn(password)
        val hasUpperCase = RegexConstants.UPPER_CASE_REGEX.containsMatchIn(password)
        val hasDigitCase = RegexConstants.DIGIT_REGEX.containsMatchIn(password)
        val hasSpecialChar = RegexConstants.SPECIAL_REGEX.containsMatchIn(password)

        val criteriaCount = listOf(hasLowerCase, hasUpperCase, hasDigitCase, hasSpecialChar).count { it }

        return criteriaCount >= Constants.PASSWORD_CRITERIA_COUNT
    }

    /**
     * 이메일과 비밀번호를 설정하는 함수.
     */
    fun setEmailAndPassword(email: String, password: String) {
        this.email = email
        this.password = password
    }
}