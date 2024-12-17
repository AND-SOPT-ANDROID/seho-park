package org.sopt.and.presentation.auth.signin.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.utils.PreferenceUtil
import org.sopt.and.domain.entity.BaseResult
import org.sopt.and.domain.entity.UserData
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.presentation.auth.signin.SignInContract.SignInUiEffect
import org.sopt.and.presentation.auth.signin.SignInContract.SignInUiEvent
import org.sopt.and.presentation.auth.signin.SignInContract.SignInUiState
import org.sopt.and.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: SignInUseCase,
    private val preferenceUtil: PreferenceUtil
) : BaseViewModel<SignInUiState, SignInUiEvent, SignInUiEffect>(SignInUiState()) {
    override fun reduceState(event: SignInUiEvent) {
        when (event) {
            is SignInUiEvent.UpdateUserName -> {
                updateState(
                    currentState.copy(
                        username = event.username
                    )
                )
            }

            is SignInUiEvent.UpdatePassword -> {
                updateState(
                    currentState.copy(
                        password = event.password
                    )
                )
            }

            is SignInUiEvent.SignInFormSubmit -> signIn()

            is SignInUiEvent.NavigateUp -> postEffect(SignInUiEffect.NavigateUp)
        }
    }

    fun signIn() {
        updateState(
            currentState.copy(
                isLoading = true
            )
        )
        viewModelScope.launch {
            when (
                val result = loginUseCase(
                    with(currentState) {
                        UserData(username, password, "")
                    }
                )
            ) {
                is BaseResult.Success -> {
                    updateState(
                        currentState.copy(
                            isLoading = false
                        )
                    )
                    preferenceUtil.saveUserToken(result.data.token)
                    postEffect(SignInUiEffect.ShowSuccessSnackBar)
                    postEffect(SignInUiEffect.NavigateToMy)
                }

                is BaseResult.Error -> {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    )
                    postEffect(SignInUiEffect.ShowErrorSnackBar(result.message))
                }
            }
        }
    }
}
