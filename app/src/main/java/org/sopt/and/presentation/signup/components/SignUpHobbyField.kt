package org.sopt.and.presentation.signup.components

import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.presentation.components.SignInOrSignUpTextField

@Composable
fun SignUpHobbyField(
    signUpHobby: String,
    onSignUpHobbyChange: (String) -> Unit
) {
    SignInOrSignUpTextField(
        information = signUpHobby,
        onValueChange = onSignUpHobbyChange,
        placeholder = R.string.sign_up_hobby_placeholder
    )
}