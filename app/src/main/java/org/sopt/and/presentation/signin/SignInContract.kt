package org.sopt.and.presentation.auth.signin

import org.sopt.and.presentation.util.UiEffect
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiState

class SignInContract {
    data class SignInUiState(
        val username: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    ) : UiState

    sealed class SignInUiEvent : UiEvent {
        data class UpdateUserName(val username: String) : SignInUiEvent()
        data class UpdatePassword(val password: String) : SignInUiEvent()
        data object SignInFormSubmit : SignInUiEvent()
        data object NavigateUp : SignInUiEvent()
    }

    sealed class SignInUiEffect : UiEffect {
        data object ShowSuccessSnackBar : SignInUiEffect()
        data class ShowErrorSnackBar(val message: String) : SignInUiEffect()
        data object NavigateToSignUp : SignInUiEffect()
        data object NavigateToMy : SignInUiEffect()
        data object NavigateUp : SignInUiEffect()
    }
}