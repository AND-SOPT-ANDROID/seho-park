package org.sopt.and.presentation.main

import org.sopt.and.presentation.util.UiEffect
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiState

class MainContract {
    data class MainUiState(
        val userToken: String? = null,
        val isLoading: Boolean = false
    ) : UiState

    sealed class MainUiEvent : UiEvent {
        data object LoadUserToken : MainUiEvent()
    }

    sealed class MainUiEffect : UiEffect
}