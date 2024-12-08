package org.sopt.and.presentation.signin

data class SignInUiState(
    val signInUsername: String = "",
    val signInPassword: String = "",
    val isSignInPasswordVisible: Boolean = false
)

sealed class SignInResult {
    object Initial : SignInResult()
    object Success : SignInResult()
    object FailurePasswordLength : SignInResult()
    object FailureWrongPassword : SignInResult()
}