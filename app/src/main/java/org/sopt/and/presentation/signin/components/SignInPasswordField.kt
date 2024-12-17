package org.sopt.and.presentation.signin.components

import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.presentation.components.ShowOrHideToggle
import org.sopt.and.presentation.components.SignInOrSignUpTextField
import org.sopt.and.presentation.util.Utils.transformationPasswordVisual

@Composable
fun SignInPasswordField(
    signInPassword: String,
    onSignInPasswordChange: (String) -> Unit,
    isSignInPasswordVisible: Boolean,
) {
    SignInOrSignUpTextField(
        information = signInPassword,
        onValueChange = onSignInPasswordChange,
        placeholder = R.string.sign_in_password_placeholder,
        visualTransformation = transformationPasswordVisual(isSignInPasswordVisible),
    )
}