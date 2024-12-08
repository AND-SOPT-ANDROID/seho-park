package org.sopt.and.presentation.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.model.SignUpInformationEntity
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.presentation.util.Utils.showToast


class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val _signUpResult = MutableStateFlow<SignUpResult>(SignUpResult.Initial)
    val signUpResult: StateFlow<SignUpResult> = _signUpResult.asStateFlow()

    private fun initSignUpResult() {
        _signUpResult.value = SignUpResult.Initial
    }

    fun setSignUpUsername(signUpUsername: String) {
        _uiState.value = _uiState.value.copy(
            signUpUsername = signUpUsername
        )
    }

    fun setSignUpPassword(signUpPassword: String) {
        _uiState.value = _uiState.value.copy(
            signUpPassword = signUpPassword
        )
    }

    fun setSignUpHobby(signUpHobby: String) {
        _uiState.value = _uiState.value.copy(
            signUpHobby = signUpHobby
        )
    }

    fun changeSignUpPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isSignUpPasswordVisible = !_uiState.value.isSignUpPasswordVisible
        )
    }

    fun signUp(
        signUpUsername: String,
        signUpPassword: String,
        signUpHobby: String
    ) {
        viewModelScope.launch {
            signUpUseCase(
                request = SignUpInformationEntity(
                    username = signUpUsername,
                    password = signUpPassword,
                    hobby = signUpHobby
                )
            ).onSuccess { signUpResponseEntity ->
                if (signUpResponseEntity.status == 200) {
                    _signUpResult.value = SignUpResult.Success
                } else if (signUpResponseEntity.code == SignUpFailureCase.FAILURE_LENGTH.errorCode
                    && signUpResponseEntity.status == SignUpFailureCase.FAILURE_LENGTH.statusCode
                ) {
                    _signUpResult.value = SignUpResult.FailureInformationLength
                } else if (signUpResponseEntity.code == SignUpFailureCase.FAILURE_DUPLICATE_USERNAME.errorCode
                    && signUpResponseEntity.status == SignUpFailureCase.FAILURE_DUPLICATE_USERNAME.statusCode
                ) {
                    _signUpResult.value = SignUpResult.FailureDuplicateUsername
                }
            }
        }
    }

    fun confirmSignUp(
        context: Context,
        onSignUpComplete: () -> Unit
    ) {
        when (signUpResult.value) {
            is SignUpResult.Success -> {
                context.showToast(message = R.string.sign_up_success)
                initSignUpResult()
                onSignUpComplete()
            }

            is SignUpResult.FailureDuplicateUsername -> {
                context.showToast(message = R.string.sign_up_failed_duplicate_username)
                initSignUpResult()
            }

            is SignUpResult.FailureInformationLength -> {
                context.showToast(message = R.string.sign_up_failed_information_length)
                initSignUpResult()
            }

            else -> {}
        }
    }
}

data class SignUpFailureCase(
    val statusCode: Int,
    val errorCode: String
) {
    companion object {
        val FAILURE_LENGTH = SignUpFailureCase(statusCode = 400, errorCode = "01")
        val FAILURE_DUPLICATE_USERNAME = SignUpFailureCase(statusCode = 409, errorCode = "00")
    }
}