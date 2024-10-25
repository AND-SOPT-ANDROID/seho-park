package org.sopt.and.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    private val savedEmail = "user@example.com" // Mock email for sign-in validation
    private val savedPassword = "password123"   // Mock password for sign-in validation

    companion object Constants {
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

    /** Validates both email and password */
    fun validateSignInOrUp(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    /** Checks email validity and sets error message if invalid */
    private fun isEmailValid(): Boolean {
        emailError = when {
            email.isEmpty() -> "이메일을 입력하세요."
            !RegexConstants.EMAIL_REGEX.matches(email) -> "이메일 형식이 올바르지 않습니다."
            else -> ""
        }
        return emailError.isEmpty()
    }

    /** Checks password validity based on length and complexity */
    private fun isPasswordValid(): Boolean {
        passwordError = when {
            password.isEmpty() -> "비밀번호를 입력하세요."
            password.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH ->
                "비밀번호는 $MIN_PASSWORD_LENGTH-${MAX_PASSWORD_LENGTH}자여야 합니다."
            !hasSufficientComplexity(password) ->
                "비밀번호는 영문 대소문자, 숫자, 특수문자 중 3가지 이상을 포함해야 합니다."
            else -> ""
        }
        return passwordError.isEmpty()
    }

    /** Helper to check for required character complexity in the password */
    private fun hasSufficientComplexity(password: String): Boolean {
        val criteriaCount = listOf(
            RegexConstants.LOWER_CASE_REGEX.containsMatchIn(password),
            RegexConstants.UPPER_CASE_REGEX.containsMatchIn(password),
            RegexConstants.DIGIT_REGEX.containsMatchIn(password),
            RegexConstants.SPECIAL_REGEX.containsMatchIn(password)
        ).count { it }
        return criteriaCount >= PASSWORD_CRITERIA_COUNT
    }

    /** Checks if entered email and password match saved credentials */
    fun validateSignIn(): Boolean {
        return email == savedEmail && password == savedPassword
    }

    /** Sets email and password when signing up */
    fun setEmailAndPassword(email: String, password: String) {
        this.email = email
        this.password = password
    }
}