package org.sopt.and.presentation.signin

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.service.AppContext
import org.sopt.and.data.service.TokenManager
import org.sopt.and.domain.model.SignInInformationEntity
import org.sopt.and.domain.model.SignInResponseEntity
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.presentation.util.Utils.showSnackbar

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val tokenManager = TokenManager(AppContext.get())

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    private val _signInResult = MutableStateFlow<SignInResult>(SignInResult.Initial)
    val signInResult: StateFlow<SignInResult> = _signInResult.asStateFlow()

    private fun initSignInResult() {
        _signInResult.value = SignInResult.Initial
    }

    fun setSignInUsername(signInUsername: String) {
        _uiState.value = _uiState.value.copy(
            signInUsername = signInUsername
        )
    }

    fun setSignInPassword(signInPassword: String) {
        _uiState.value = _uiState.value.copy(
            signInPassword = signInPassword
        )
    }

    fun changeSignInPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isSignInPasswordVisible = !_uiState.value.isSignInPasswordVisible
        )
    }

    fun signIn(
        signInUsername: String,
        signInPassword: String
    ) {
        viewModelScope.launch {
            signInUseCase(
                request = SignInInformationEntity(
                    username = signInUsername,
                    password = signInPassword
                )
            ).onSuccess { signInResponseEntity: SignInResponseEntity ->
                if (signInResponseEntity.status == 200) {
                    _signInResult.value = SignInResult.Success
                    signInResponseEntity.token?.let { token ->
                        tokenManager.saveToken(token)
                    }
                } else if (signInResponseEntity.code == SignInFailureCase.FAILURE_LENGTH.errorCode
                    && signInResponseEntity.status == SignInFailureCase.FAILURE_LENGTH.statusCode
                ) {
                    _signInResult.value = SignInResult.FailurePasswordLength
                } else if (signInResponseEntity.code == SignInFailureCase.FAILURE_WRONG_PASSWORD.errorCode
                    && signInResponseEntity.status == SignInFailureCase.FAILURE_WRONG_PASSWORD.statusCode
                ) {
                    _signInResult.value = SignInResult.FailureWrongPassword
                }
            }
        }
    }

    fun confirmLogin(
        snackbarHostState: SnackbarHostState,
        navigateToMyInfo: () -> Unit,
        context: Context,
        scope: CoroutineScope
    ) {
        when (signInResult.value) {
            is SignInResult.Success -> {
                context.showSnackbar(
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    message = R.string.sign_in_success_message,
                )
                navigateToMyInfo()
                initSignInResult()
            }

            is SignInResult.FailurePasswordLength -> {
                context.showSnackbar(
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    message = R.string.sign_in_failed_password_length,
                )
                initSignInResult()
            }

            is SignInResult.FailureWrongPassword -> {
                context.showSnackbar(
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    message = R.string.sign_in_failed_wrong_password,
                )
                initSignInResult()
            }

            else -> {}
        }
    }
}

data class SignInFailureCase(
    val statusCode: Int,
    val errorCode: String
) {
    companion object {
        val FAILURE_LENGTH = SignInFailureCase(statusCode = 400, errorCode = "01")
        val FAILURE_WRONG_PASSWORD = SignInFailureCase(statusCode = 403, errorCode = "01")
    }
}