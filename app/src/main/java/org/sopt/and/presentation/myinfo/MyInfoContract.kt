package org.sopt.and.presentation.myinfo

import org.sopt.and.presentation.util.UiEffect
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiState

class MyInfoContract {
    data class MyPageUiState(
        val hobby: String = "",
        val isLoading: Boolean = false,
        val isLoggedOut: Boolean = false,
        val errorMessage: String? = null,
        val tokenInvalid: Boolean = false
    ) : UiState

    sealed class MyPageUiEvent : UiEvent {
        data object Logout : MyPageUiEvent()
        data object LoadHobby : MyPageUiEvent()
    }

    sealed class MyPageUiEffect : UiEffect {
        data class ShowErrorSnackBar(val message: String) : MyPageUiEffect()
        data object NavigateToSignIn : MyPageUiEffect()
    }
}