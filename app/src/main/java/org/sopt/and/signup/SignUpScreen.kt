package org.sopt.and.signup

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpScreen() {
    val context = LocalContext.current
    SignUp(
        onSignUpSuccess = { email, password ->
            val bundle = Bundle().apply {
                putString("email", email)
                putString("password", password)
            }
            val intent = Intent(context, SignInActivity::class.java).apply {
                putExtras(bundle)
            }
            context.startActivity(intent)
            Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
        },
        onSignUpFailure = {
            Toast.makeText(context, "회원가입 실패: 입력 정보를 확인해주세요.", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun Header() {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(50.dp))
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "회원가입",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = "x",
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable {
                    context.startActivity(Intent(context, SignInActivity::class.java))
                }
        )
    }
}

@Composable
fun SignUpView(onSignUpSuccess: (String, String) -> Unit, onSignUpFailure: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val validator = PasswordValidator()

    Column(modifier = Modifier.fillMaxWidth().background(Color.Black)) {
        TitleText()

        Spacer(modifier = Modifier.height(30.dp))

        // 이메일 입력 필드
        EmailField(email, emailError) {
            email = it
            emailError = !validateEmail(it)
        }

        // 비밀번호 입력 필드 및 show/hide 버튼
        PasswordField(password, isPasswordVisible, passwordError) {
            password = it
            passwordError = !validator.validatePassword(it)
        }

        Spacer(modifier = Modifier.weight(1f))

        // 회원가입 버튼
        SignUpButton(
            emailError = emailError,
            passwordError = passwordError,
            email = email,
            password = password,
            onSignUpSuccess = onSignUpSuccess,
            onSignUpFailure = onSignUpFailure
        )
    }
}

@Composable
fun TitleText() {
    val firstText = AnnotatedString.Builder().apply {
        withStyle(style = SpanStyle(color = Color.White)) { append("이메일과 비밀번호") }
        withStyle(style = SpanStyle(color = Color.Gray)) { append("만으로") }
    }.toAnnotatedString()

    val secondText = AnnotatedString.Builder().apply {
        withStyle(style = SpanStyle(color = Color.White)) { append("Wavve를 즐길 수") }
        withStyle(style = SpanStyle(color = Color.Gray)) { append(" 있어요!") }
    }.toAnnotatedString()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 30.dp, end = 15.dp)
    ) {
        Text(text = firstText, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        Text(text = secondText, fontSize = 20.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun EmailField(email: String, emailError: Boolean, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
        value = email,
        onValueChange = onValueChange,
        label = { Text(text = "wavve@example.com", color = Color.DarkGray) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.Gray
        )
    )
    Text(
        text = "정확한 이메일을 입력해 주세요.",
        fontSize = 10.sp,
        color = Color.Gray,
        modifier = Modifier.padding(start = 15.dp, top = 5.dp)
    )
}

@Composable
fun PasswordField(
    password: String,
    isPasswordVisible: Boolean,
    passwordError: Boolean,
    onValueChange: (String) -> Unit
) {
    Box {
        TextField(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
            value = password,
            onValueChange = onValueChange,
            label = { Text(text = "Wavve 비밀번호 설정", color = Color.DarkGray) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        TextButton(
            onClick = { isPasswordVisible = !isPasswordVisible },
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 8.dp)
        ) {
            Text(text = if (isPasswordVisible) "Hide" else "Show", color = Color.White)
        }
    }
    Text(
        text = "비밀번호는 8~20자 이내로 설정해주세요.",
        fontSize = 10.sp,
        color = Color.Gray,
        modifier = Modifier.padding(start = 15.dp, top = 5.dp)
    )
}

@Composable
fun SignUpButton(
    emailError: Boolean,
    passwordError: Boolean,
    email: String,
    password: String,
    onSignUpSuccess: (String, String) -> Unit,
    onSignUpFailure: () -> Unit
) {
    TextButton(
        onClick = {
            if (!emailError && !passwordError && email.isNotEmpty() && password.isNotEmpty()) {
                onSignUpSuccess(email, password)
            } else {
                onSignUpFailure()
            }
        },
        modifier = Modifier.fillMaxWidth().background(Color.Gray.copy(alpha = 0.5f)).padding(vertical = 15.dp)
    ) {
        Text(
            text = "Wavve 회원가입",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

fun validateEmail(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    return emailRegex.matches(email)
}

class PasswordValidator {
    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 20
    }

    fun validatePassword(password: String): Boolean {
        if (password.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH) return false
        val criteriaCnt = listOf(
            Regex("[a-z]").containsMatchIn(password),
            Regex("[A-Z]").containsMatchIn(password),
            Regex("[0-9]").containsMatchIn(password),
            Regex("[!@#\$%^&*(),.?\\\":{}|<>]").containsMatchIn(password)
        ).count { it }
        return criteriaCnt >= 3
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}