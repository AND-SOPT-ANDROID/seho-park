package org.sopt.and.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.utils.extension.noRippleClickable
import org.sopt.and.presentation.auth.signup.SignUpContract
import org.sopt.and.presentation.auth.signup.viewmodel.SignUpViewModel
import org.sopt.and.presentation.components.SnSBox
import org.sopt.and.presentation.signup.components.SignUpButton
import org.sopt.and.presentation.signup.components.SignUpGreetingText
import org.sopt.and.presentation.signup.components.SignUpHobbyField
import org.sopt.and.presentation.signup.components.SignUpPasswordField
import org.sopt.and.presentation.signup.components.SignUpTopBar
import org.sopt.and.presentation.signup.components.SignUpUsernameField
import org.sopt.and.presentation.util.Utils.showToast
import org.sopt.and.ui.theme.Black100
import org.sopt.and.ui.theme.Blue100
import org.sopt.and.ui.theme.WavveDisabled
import org.sopt.and.ui.theme.White100

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val signUpState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignUpContract.SignUpUiEffect.ShowSuccessToast -> {
                    context.showToast(context.getString(R.string.sign_up_toast_success))
                    navigateToSignIn()
                }

                is SignUpContract.SignUpUiEffect.ShowErrorToast -> {
                    context.showToast(effect.message)
                }

                is SignUpContract.SignUpUiEffect.NavigateToSignIn -> navigateToSignIn()
                is SignUpContract.SignUpUiEffect.NavigateUp -> navigateUp()
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black100)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp)
        ) {
            SignUpTopBar()

            Spacer(modifier = Modifier.height(30.dp))

            SignUpGreetingText(fontSize = 24)

            Spacer(modifier = Modifier.height(20.dp))

            SignUpUsernameField(
                signUpUsername = signUpState.username,
                onSignUpUsernameChange = {
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdateUserName(
                            it
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            SignUpPasswordField(
                signUpPassword = signUpState.password,
                onSignUpPasswordChange = {
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdatePassword(
                            it
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))


            SignUpHobbyField(
                signUpHobby = signUpState.hobby,
                onSignUpHobbyChange = {
                    viewModel.sendEvent(
                        SignUpContract.SignUpUiEvent.UpdateHobby(it)
                    )
                }
            )

            Spacer(modifier = Modifier.size(40.dp))

            SnSBox(stringResource(R.string.sign_in_link_with_another_service_title))
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (signUpState.isValid) Blue100 else WavveDisabled
                )
                .wrapContentHeight()
                .noRippleClickable { viewModel.sendEvent(SignUpContract.SignUpUiEvent.SignUpFormSubmit) }
                .padding(vertical = 14.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up_text_wavve_sign_up),
                color = White100
            )
        }
    }
}