package org.sopt.and.presentation.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.utils.PreferenceUtil
import org.sopt.and.presentation.util.BaseViewModel
import org.sopt.and.presentation.main.MainContract.MainUiEffect
import org.sopt.and.presentation.main.MainContract.MainUiEvent
import org.sopt.and.presentation.main.MainContract.MainUiState
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val preferenceUtil: PreferenceUtil
) : BaseViewModel<MainUiState, MainUiEvent, MainUiEffect>(MainUiState()) {
    override fun reduceState(event: MainUiEvent) {
        when (event) {
            MainUiEvent.LoadUserToken -> loadUserToken()
        }
    }

    private fun loadUserToken() {
        updateState(
            currentState.copy(
                isLoading = true
            )
        )
        viewModelScope.launch {
            val token = preferenceUtil.getUserToken()
            updateState(
                currentState.copy(
                    isLoading = false,
                    userToken = token
                )
            )
        }
    }
}