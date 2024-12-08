package org.sopt.and.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.presentation.components.SnSBox
import org.sopt.and.presentation.signin.components.SignInButton
import org.sopt.and.presentation.signin.components.SignInPasswordField
import org.sopt.and.presentation.signin.components.SignInToAdditionalFeatures
import org.sopt.and.presentation.signin.components.SignInTopBar
import org.sopt.and.presentation.signin.components.SignInUsernameField
import org.sopt.and.presentation.viewmodelfactory.SignInViewModelFactory
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black100

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMyInfo: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val signInViewModel: SignInViewModel = viewModel(
        factory = SignInViewModelFactory()
    )
    val signInUiState by signInViewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val signInResult by signInViewModel.signInResult.collectAsStateWithLifecycle()

    val signInUsername = signInUiState.signInUsername
    val signInPassword = signInUiState.signInPassword
    val isSignInPasswordVisible = signInUiState.isSignInPasswordVisible

    LaunchedEffect(signInResult) {
        signInViewModel.confirmLogin(
            snackbarHostState = snackbarHostState,
            navigateToMyInfo = navigateToMyInfo,
            context = context,
            scope = scope
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Black100)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInTopBar()

                Spacer(modifier = Modifier.height(60.dp))

                SignInUsernameField(
                    signInUsername = signInUsername,
                    onSignInUsernameChange = signInViewModel::setSignInUsername
                )

                Spacer(modifier = Modifier.height(5.dp))

                SignInPasswordField(
                    signInPassword = signInPassword,
                    onSignInPasswordChange = signInViewModel::setSignInPassword,
                    isSignInPasswordVisible = isSignInPasswordVisible,
                    onVisibilityChange = signInViewModel::changeSignInPasswordVisibility
                )

                Spacer(modifier = Modifier.height(30.dp))

                SignInButton(
                    signIn = signInViewModel::signIn,
                    signInUsername = signInUiState.signInUsername,
                    signInPassword = signInUiState.signInPassword
                )

                Spacer(modifier = Modifier.height(20.dp))

                SignInToAdditionalFeatures(navigateToSignUp = navigateToSignUp)

                Spacer(modifier = Modifier.size(40.dp))

                SnSBox(stringResource(R.string.sign_in_link_with_another_service_title))
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SignInScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            innerPadding
            SignInScreen(
                navigateToSignUp = {},
                navigateToMyInfo = {}
            )
        }
    }
}