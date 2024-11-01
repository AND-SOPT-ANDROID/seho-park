package org.sopt.and.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun AuthSignButton(
    buttonText: String,
    validateAction: () -> Boolean,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    buttonColor: Color = Color.Blue
) {
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            coroutineScope.launch {
                if (validateAction()) {
                    snackbarHostState.showSnackbar("$buttonText 성공!")
                    onSuccess()
                } else {
                    snackbarHostState.showSnackbar("$buttonText 실패: 이메일과 비밀번호를 확인해주세요.")
                    onFailure()
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}