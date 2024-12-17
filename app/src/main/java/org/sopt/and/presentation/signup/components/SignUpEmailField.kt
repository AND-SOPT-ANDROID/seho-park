package org.sopt.and.presentation.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.components.CautionBox
import org.sopt.and.presentation.components.SignInOrSignUpTextField

@Composable
fun SignUpUsernameField(
    signUpUsername: String,
    onSignUpUsernameChange: (String) -> Unit
) {
    Column {
        SignInOrSignUpTextField(
            information = signUpUsername,
            onValueChange = onSignUpUsernameChange,
            placeholder = R.string.sign_up_username_placeholder
        )

        Spacer(modifier = Modifier.height(10.dp))

        CautionBox(
            contentDescription = R.string.sign_up_username_description,
            caution = R.string.sign_up_username_caution
        )
    }
}