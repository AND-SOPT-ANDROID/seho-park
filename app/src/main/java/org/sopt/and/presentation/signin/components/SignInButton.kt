package org.sopt.and.presentation.auth.signin.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.Blue100
import org.sopt.and.ui.theme.White100

@Composable
fun SignInButton(
    text: String,
    onClick : () -> Unit,
    modifier: Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue100
        ),
        onClick = onClick,
    ) {
        Text(
            text = text,
            color = White100,
            fontSize = 16.sp,
            modifier = modifier.padding(vertical = 8.dp)
        )
    }
}