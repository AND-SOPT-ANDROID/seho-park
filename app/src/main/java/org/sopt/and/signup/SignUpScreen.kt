package org.sopt.and.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.viewmodel.SignViewModel

@Composable
fun SignUpScreen(
    signViewModel: SignViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SignUpHeader(onNavigateToSignIn)
        Spacer(modifier = Modifier.height(20.dp))
        SignUpText()
        Spacer(modifier = Modifier.height(30.dp))

        SignUpTextField(
            label = "wavve@example.com",
            textValue = signViewModel.email,
            onTextChanged = { signViewModel.email = it },
            errorMessage = if (signViewModel.email.isEmpty()) "⚠️ 정확한 이메일을 입력해 주세요." else ""
        )
        Spacer(modifier = Modifier.height(10.dp))

        SignUpTextField(
            label = "ex) abcdEFG123",
            textValue = signViewModel.password,
            onTextChanged = { signViewModel.password = it },
            isPasswordField = true,
            isPasswordVisible = signViewModel.isPasswordVisible,
            onPasswordToggle = {
                signViewModel.isPasswordVisible = !signViewModel.isPasswordVisible
            },
            errorMessage = "⚠️ 비밀번호는 8~20자 이내로 설정해 주세요."
        )

        Spacer(modifier = Modifier.weight(1f))

        SignUpButton(
            email = signViewModel.email,
            password = signViewModel.password,
            onSignUpSuccess = {
                Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                onNavigateToSignIn()
            },
            onSignUpFailure = {
                Toast.makeText(context, "회원가입 실패: 입력 정보를 확인해주세요.", Toast.LENGTH_SHORT).show()
            },
            performSignUp = { signViewModel.performSignUp() }
        )
    }
}

@Composable
fun SignUpHeader(onNavigateToSignIn: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "회원가입",
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "❌",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable { onNavigateToSignIn() }
        )
    }
}

@Composable
fun SignUpText() {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
            append("이메일과 비밀번호")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
            append("만으로\n")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
            append("Wavve를 즐길 수 ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
            append("있어요!")
        }
    }

    Text(
        text = annotatedText,
        color = Color.White,
        fontSize = 28.sp,
        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
    )
}

@Composable
fun SignUpTextField(
    label: String,
    textValue: String,
    onTextChanged: (String) -> Unit,
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPasswordToggle: (() -> Unit)? = null,
    errorMessage: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        TextField(
            value = textValue,
            onValueChange = onTextChanged,
            placeholder = { Text(text = label, color = Color.DarkGray) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray
            ),
            visualTransformation = if (isPasswordField && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = if (isPasswordField) {
                {
                    TextButton(onClick = { onPasswordToggle?.invoke() }) {
                        Text(text = if (isPasswordVisible) "Hide" else "Show", color = Color.White)
                    }
                }
            } else null
        )
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
fun SignUpButton(
    email: String,
    password: String,
    onSignUpSuccess: () -> Unit,
    onSignUpFailure: () -> Unit,
    performSignUp: () -> Unit
) {
    TextButton(
        onClick = {
            if (email.isNotEmpty() && password.length in 8..20) {
                performSignUp()
                onSignUpSuccess()
            } else {
                onSignUpFailure()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray.copy(alpha = 0.5f))
            .padding(15.dp)
    ) {
        Text(
            text = "Wavve 회원가입",
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}