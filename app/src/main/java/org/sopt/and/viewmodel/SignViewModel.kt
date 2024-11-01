package org.sopt.and.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences: SharedPreferences by lazy {
        application.getSharedPreferences("user_prefs", Application.MODE_PRIVATE)
    }

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    private var emailError by mutableStateOf("")
    private var passwordError by mutableStateOf("")


    /** Sign-up: Save email and password to SharedPreferences */
    fun performSignUp() {
        if (validateSignInOrUp()) {
            viewModelScope.launch(Dispatchers.IO) {
                preferences.edit().apply {
                    putString("saved_email", email)
                    putString("saved_password", password)
                    apply()
                }
                Log.d("SignViewModel", "Email and Password saved: $email, $password")
            }
        } else {
            Log.d("SignViewModel", "Validation failed. Email: $email, Password: $password")
        }
    }

    /** Validate email and password during sign-in */
    fun validateSignIn(): Boolean {
        val savedEmail = preferences.getString("saved_email", "")
        val savedPassword = preferences.getString("saved_password", "")
        Log.d("SignViewModel", "Loaded Saved Email: $savedEmail, Saved Password: $savedPassword")
        return email == savedEmail && password == savedPassword
    }

    /** Validates both email and password */
    fun validateSignInOrUp(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    /** Checks if the email meets the required format */
    private fun isEmailValid(): Boolean {
        emailError = when {
            email.isEmpty() -> "이메일을 입력하세요."
            !Constants.EMAIL_REGEX.matches(email) -> "이메일 형식이 올바르지 않습니다."
            else -> ""
        }
        return emailError.isEmpty()
    }

    /** Checks if the password meets length and complexity requirements */
    private fun isPasswordValid(): Boolean {
        passwordError = when {
            password.isEmpty() -> "비밀번호를 입력하세요."
            password.length !in Constants.MIN_PASSWORD_LENGTH..Constants.MAX_PASSWORD_LENGTH ->
                "비밀번호는 ${Constants.MIN_PASSWORD_LENGTH}-${Constants.MAX_PASSWORD_LENGTH}자여야 합니다."
            !isPasswordComplexEnough(password) ->
                "비밀번호는 영문 대소문자, 숫자, 특수문자 중 3가지 이상을 포함해야 합니다."
            else -> ""
        }
        return passwordError.isEmpty()
    }

    /** Helper to check if password meets complexity requirements */


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