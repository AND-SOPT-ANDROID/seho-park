package org.sopt.and.presentation.signup.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.signup.SignUpViewModel
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun SignUpButton(
    signUpUsername: String,
    signUpPassword: String,
    signUpHobby: String,
    onSignUpComplete: () -> Unit,
    signUpViewModel: SignUpViewModel
) {
    val signUpResult by signUpViewModel.signUpResult.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(signUpResult) {
        signUpViewModel.confirmSignUp(
            context = context,
            onSignUpComplete = onSignUpComplete
        )
    }

    Button(
        onClick = {
            signUpViewModel.signUp(
                signUpUsername = signUpUsername,
                signUpPassword = signUpPassword,
                signUpHobby = signUpHobby
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Grey200,
            contentColor = White100
        )
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_button),
            style = TextStyle(fontSize = 18.sp)
        )
    }
}