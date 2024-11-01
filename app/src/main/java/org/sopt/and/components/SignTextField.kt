package org.sopt.and.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    labelResId: Int,
    textValue: String,
    onTextChanged: (String) -> Unit,
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordToggle: (() -> Unit)? = null,
    showHint: Boolean = false,  // 추가된 파라미터
    hintResId: Int? = null,  // 추가된 파라미터
    modifier: Modifier = Modifier
) {
    Column {
        TextField(
            value = textValue,
            onValueChange = onTextChanged,
            label = { Text(text = stringResource(id = labelResId)) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray
            ),
            visualTransformation = if (isPasswordField && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = if (isPasswordField) {
                {
                    TextButton(onClick = { onPasswordToggle?.invoke() }) {
                        Text(
                            text = if (isPasswordVisible) "Hide" else "Show",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            } else null,
            modifier = modifier
        )

        // 비밀번호 안내 텍스트
        if (showHint && hintResId != null) {
            Text(
                text = stringResource(id = hintResId),
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}