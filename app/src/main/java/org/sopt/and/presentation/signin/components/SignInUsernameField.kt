package org.sopt.and.presentation.signin.components

import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.presentation.components.SignInOrSignUpTextField

@Composable
fun SignInUsernameField(
    signInUsername: String,
    onSignInUsernameChange: (String) -> Unit
) {
    SignInOrSignUpTextField(
        information = signInUsername,
        onValueChange = onSignInUsernameChange,
        placeholder = R.string.sign_in_username_placeholder
    )
}