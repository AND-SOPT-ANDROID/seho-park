package org.sopt.and.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.presentation.components.SnSBox
import org.sopt.and.presentation.signup.components.SignUpButton
import org.sopt.and.presentation.signup.components.SignUpGreetingText
import org.sopt.and.presentation.signup.components.SignUpHobbyField
import org.sopt.and.presentation.signup.components.SignUpPasswordField
import org.sopt.and.presentation.signup.components.SignUpTopBar
import org.sopt.and.presentation.signup.components.SignUpUsernameField
import org.sopt.and.presentation.viewmodelfactory.SignUpViewModelFactory
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black100

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit,
) {
    val signUpViewModel: SignUpViewModel = viewModel(
        factory = SignUpViewModelFactory()
    )
    val signUpUiState by signUpViewModel.uiState.collectAsStateWithLifecycle()

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
                signUpUsername = signUpUiState.signUpUsername,
                onSignUpUsernameChange = signUpViewModel::setSignUpUsername
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignUpPasswordField(
                signUpPassword = signUpUiState.signUpPassword,
                onSignUpPasswordChange = signUpViewModel::setSignUpPassword,
                isSignUpPasswordVisible = signUpUiState.isSignUpPasswordVisible,
                onVisibilityChange = signUpViewModel::changeSignUpPasswordVisibility
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignUpHobbyField(
                signUpHobby = signUpUiState.signUpHobby,
                onSignUpHobbyChange = signUpViewModel::setSignUpHobby
            )

            Spacer(modifier = Modifier.size(40.dp))

            SnSBox(stringResource(R.string.sign_in_link_with_another_service_title))
        }

        SignUpButton (
            signUpUsername = signUpUiState.signUpUsername,
            signUpPassword = signUpUiState.signUpPassword,
            signUpHobby = signUpUiState.signUpHobby,
            onSignUpComplete = navigateToSignIn,
            signUpViewModel = signUpViewModel
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignUpScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            SignUpScreen(
                modifier = Modifier
                    .padding(innerPadding),
                navigateToSignIn = { }
            )
        }
    }
}