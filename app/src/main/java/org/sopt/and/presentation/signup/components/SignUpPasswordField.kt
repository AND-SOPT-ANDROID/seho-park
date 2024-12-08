package org.sopt.and.presentation.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.components.CautionBox
import org.sopt.and.presentation.components.ShowOrHideToggle
import org.sopt.and.presentation.components.SignInOrSignUpTextField
import org.sopt.and.presentation.util.Utils.transformationPasswordVisual

@Composable
fun SignUpPasswordField(
    signUpPassword: String,
    onSignUpPasswordChange: (String) -> Unit,
    isSignUpPasswordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Column {
        SignInOrSignUpTextField(
            information = signUpPassword,
            onValueChange = onSignUpPasswordChange,
            placeholder = R.string.sign_up_password_placeholder,
            visualTransformation = transformationPasswordVisual(isSignUpPasswordVisible),
            trailingIcon = {
                ShowOrHideToggle(isSignUpPasswordVisible, onVisibilityChange)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        CautionBox(
            contentDescription = R.string.sign_up_password_description,
            caution = R.string.sign_up_password_caution
        )
    }
}