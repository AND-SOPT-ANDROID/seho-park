package org.sopt.and.presentation.auth.signup

import org.sopt.and.presentation.util.UiEffect
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiState

class SignUpContract {
    data class SignUpUiState(
        val username: String = "",
        val password: String = "",
        val hobby: String = "",
        val isUserNameValid: Boolean = false,
        val isPasswordValid: Boolean = false,
        val isHobbyValid: Boolean = false,
        val isUserNameFieldFocused: Boolean = false,
        val isPasswordFieldFocused: Boolean = false,
        val isHobbyFieldFocused: Boolean = false,
        val isValid: Boolean = false,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    ) : UiState

    sealed class SignUpUiEvent : UiEvent {
        data class UpdateUserName(val username: String) : SignUpUiEvent()
        data class UpdatePassword(val password: String) : SignUpUiEvent()
        data class UpdateHobby(val hobby: String) : SignUpUiEvent()
        data class UpdateFieldFocus(val field: Field, val isFocused: Boolean) : SignUpUiEvent()
        data object SignUpFormSubmit : SignUpUiEvent()
        data object Close : SignUpUiEvent()
    }

    sealed class SignUpUiEffect : UiEffect {
        data object ShowSuccessToast : SignUpUiEffect()
        data class ShowErrorToast(val message: String) : SignUpUiEffect()
        data object NavigateToSignIn : SignUpUiEffect()
        data object NavigateUp : SignUpUiEffect()
    }

    enum class Field {
        UserName, Password, Hobby
    }
}