package org.sopt.and.presentation.signin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey200

@Composable
fun SignInToAdditionalFeatures(
    navigateToSignUp: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.6f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_to_find_id_button),
            color = Grey200,
            style = TextStyle(
                fontSize = 11.sp
            )
        )

        Text(
            text = stringResource(id = R.string.seperator),
            color = Grey200,
            style = TextStyle(
                fontSize = 11.sp
            )
        )

        Text(
            text = stringResource(id = R.string.sign_in_to_reset_password_button),
            color = Grey200,
            style = TextStyle(
                fontSize = 11.sp
            )
        )

        Text(
            text = stringResource(id = R.string.seperator),
            color = Grey200,
            style = TextStyle(
                fontSize = 11.sp
            )
        )

        Text(
            text = stringResource(id = R.string.sign_in_to_sign_up_button),
            color = Grey200,
            modifier = Modifier.clickable { navigateToSignUp() },
            style = TextStyle(
                fontSize = 11.sp
            )
        )
    }
}