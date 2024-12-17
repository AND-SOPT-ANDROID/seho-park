package org.sopt.and.presentation.auth.signup.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.BaseResult
import org.sopt.and.domain.entity.UserData
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.presentation.auth.signup.SignUpContract
import org.sopt.and.presentation.auth.signup.SignUpContract.SignUpUiEffect
import org.sopt.and.presentation.auth.signup.SignUpContract.SignUpUiState
import org.sopt.and.presentation.auth.signup.SignUpContract.SignUpUiEvent
import org.sopt.and.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: SignUpUseCase
) : BaseViewModel<SignUpUiState, SignUpUiEvent, SignUpUiEffect>(SignUpUiState()) {
    override fun reduceState(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.UpdateUserName -> {
                val isValid = validateUserName(event.username)
                updateState(
                    currentState.copy(
                        username = event.username,
                        isUserNameValid = isValid,
                        isValid = isValid &&
                                currentState.isPasswordValid &&
                                currentState.isHobbyValid
                    )
                )
            }

            is SignUpUiEvent.UpdatePassword -> {
                val isValid = validatePassword(event.password)
                updateState(
                    currentState.copy(
                        password = event.password,
                        isPasswordValid = isValid,
                        isValid = isValid &&
                                currentState.username.isNotBlank() &&
                                currentState.hobby.isNotBlank()
                    )
                )
            }

            is SignUpUiEvent.UpdateHobby -> {
                val isValid = validateHobby(event.hobby)
                updateState(
                    currentState.copy(
                        hobby = event.hobby,
                        isHobbyValid = isValid,
                        isValid = isValid &&
                                currentState.username.isNotBlank() &&
                                currentState.password.isNotBlank()
                    )
                )
            }

            is SignUpUiEvent.UpdateFieldFocus -> {
                when (event.field) {
                    SignUpContract.Field.UserName -> updateState(
                        currentState.copy(
                            isUserNameFieldFocused = event.isFocused
                        )
                    )

                    SignUpContract.Field.Password -> updateState(
                        currentState.copy(
                            isPasswordFieldFocused = event.isFocused
                        )
                    )

                    SignUpContract.Field.Hobby -> updateState(
                        currentState.copy(
                            isHobbyFieldFocused = event.isFocused
                        )
                    )
                }
            }

            is SignUpUiEvent.SignUpFormSubmit -> signUp()

            is SignUpUiEvent.Close -> postEffect(SignUpUiEffect.NavigateUp)
        }
    }

    private fun signUp() {
        updateState(
            currentState.copy(
                isLoading = true
            )
        )
        viewModelScope.launch {
            when (val result = registerUserUseCase(
                UserData(
                    username = currentState.username,
                    password = currentState.password,
                    hobby = currentState.hobby
                )
            )) {
                is BaseResult.Success -> {
                    updateState(
                        currentState.copy(
                            isLoading = false
                        )
                    )
                    postEffect(SignUpUiEffect.ShowSuccessToast)
                    postEffect(SignUpUiEffect.NavigateToSignIn)
                }

                is BaseResult.Error -> {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    )
                    postEffect(SignUpUiEffect.ShowErrorToast(result.message))
                }
            }
        }
    }

    private fun validateUserName(username: String) =
        username.isNotBlank() && username.length <= 8

    private fun validatePassword(password: String) =
        password.isNotBlank() && password.length <= 8

    private fun validateHobby(hobby: String) =
        hobby.isNotBlank() && hobby.length <= 8
}
