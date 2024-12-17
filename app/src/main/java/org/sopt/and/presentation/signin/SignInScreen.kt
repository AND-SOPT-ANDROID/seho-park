package org.sopt.and.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.core.utils.SnackBarUtils
import org.sopt.and.presentation.auth.signin.SignInContract
import org.sopt.and.presentation.auth.signin.component.SignInButton
import org.sopt.and.presentation.auth.signin.viewmodel.SignInViewModel
import org.sopt.and.presentation.components.SnSBox
import org.sopt.and.presentation.signin.components.*
import org.sopt.and.ui.theme.Black100


@Composable
fun SignInScreen(
    navigateToMy: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val signInState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Effect handling
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignInContract.SignInUiEffect.ShowSuccessSnackBar -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        SnackBarUtils.showSnackBar(
                            message = context.getString(R.string.sign_in_snackbar_login_success),
                            actionLabel = context.getString(R.string.sign_in_snackbar_action_close)
                        )
                    }
                    navigateToMy()
                }

                is SignInContract.SignInUiEffect.ShowErrorSnackBar -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        SnackBarUtils.showSnackBar(
                            message = effect.message,
                            actionLabel = context.getString(R.string.sign_in_snackbar_action_close)
                        )
                    }
                }

                is SignInContract.SignInUiEffect.NavigateToSignUp -> navigateToSignUp()
                is SignInContract.SignInUiEffect.NavigateToMy -> navigateToMy()
                is SignInContract.SignInUiEffect.NavigateUp -> navigateUp()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { viewModel.sendEvent(SignInContract.SignInUiEvent.NavigateUp) }
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
                    signInUsername = signInState.username,
                    onSignInUsernameChange = {
                        viewModel.sendEvent(
                            SignInContract.SignInUiEvent.UpdateUserName(
                                it
                            )
                        )
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))

                SignInPasswordField(
                    signInPassword = signInState.password,
                    onSignInPasswordChange = {
                        viewModel.sendEvent(
                            SignInContract.SignInUiEvent.UpdatePassword(
                                it
                            )
                        )
                    },
                    isSignInPasswordVisible = false,
                )

                Spacer(modifier = Modifier.height(30.dp))

                SignInButton(
                    text = stringResource(R.string.sign_in_text_login),
                    onClick = { viewModel.signIn() },
                    modifier = Modifier
                )


                Spacer(modifier = Modifier.height(20.dp))

                SignInToAdditionalFeatures(navigateToSignUp = navigateToSignUp)

                Spacer(modifier = Modifier.size(40.dp))

                SnSBox(stringResource(R.string.sign_in_link_with_another_service_title))
            }
        }
    }
}