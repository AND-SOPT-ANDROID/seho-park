package org.sopt.and.presentation.signin.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.Blue100
import org.sopt.and.ui.theme.White100

@Composable
fun SignInButton(
    signIn: (String, String) -> Unit,
    signInUsername: String,
    signInPassword: String
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
            signIn(
                signInUsername, signInPassword
            )
        },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue100,
            contentColor = White100
        )
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_button)
        )
    }
}