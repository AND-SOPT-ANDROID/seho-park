package org.sopt.and.presentation.signup

data class SignUpUiState(
    val signUpUsername: String = "",
    val signUpPassword: String = "",
    val signUpHobby: String = "",
    val isSignUpPasswordVisible: Boolean = false
)

sealed class SignUpResult {
    object Initial : SignUpResult()
    object Success : SignUpResult()
    object FailureInformationLength : SignUpResult()
    object FailureDuplicateUsername : SignUpResult()
}