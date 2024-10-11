package org.sopt.and

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUp(
                    onSignUpSuccess = { email, password ->
                        // 회원가입 성공 시 로그인 화면으로 전환
                        val intent = Intent(this, SignInActivity::class.java).apply {
                            putExtra("email", email)
                            putExtra("password", password)
                        }
                        startActivity(intent)
                        Toast.makeText(application, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                    },
                    onSignUpFailure = {
                        Toast.makeText(application, "회원가입 실패: 입력 정보를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Composable
fun Header() {
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
            modifier = Modifier.align(Alignment.CenterEnd)
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

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 30.dp, end = 15.dp)
        ) {
            Text(
                text = "이메일과 비밀번호 만으로",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )

            Text(
                text = "Wavve를 즐길 수 있어요",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 이메일 입력 필드
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = email,
            onValueChange = {
                email = it
                emailError = !validateEmail(it) // 이메일 유효성 검사
            },
            label = { Text(text = "wavve@example.com", color = Color.DarkGray) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray
            )
        )

        Text(
            text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.",
            fontSize = 10.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 15.dp, top = 5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 비밀번호 입력 필드 및 show/hide 버튼
        Box {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                value = password,
                onValueChange = {
                    password = it
                    passwordError = !validatePassword(it)
                },
                label = { Text(text = "Wavve 비밀번호 설정", color = Color.DarkGray) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Gray
                ),
                placeholder = { Text("Wavve 비밀번호 설정") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )

            // 비밀번호 show/hide 버튼
            TextButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
            ) {
                Text(text = if (isPasswordVisible) "Hide" else "Show", color = Color.White)
            }
        }

        Text(
            text = "비밀번호는 8~20자 이내로, 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용해야 합니다.",
            fontSize = 10.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 15.dp, top = 5.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // 회원가입 버튼
        TextButton(
            onClick = {
                if (!emailError && !passwordError && email.isNotEmpty() && password.isNotEmpty()) {
                    onSignUpSuccess(email, password)
                } else {
                    onSignUpFailure()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.5f))
                .padding(vertical = 15.dp),
        ) {
            Text(
                text = "Wavve 회원가입",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SignUp(onSignUpSuccess: (String, String) -> Unit, onSignUpFailure: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Header()
        SignUpView(onSignUpSuccess, onSignUpFailure)
        Text(
            text = "또는 다른 서비스 계정으로 가입",
            fontSize = 10.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 15.dp, top = 5.dp)
        )
    }
}

// 이메일 유효성 검사 함수
fun validateEmail(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    return emailRegex.matches(email)
}

// 비밀번호 유효성 검사 함수
fun validatePassword(password: String): Boolean {
    if (password.length !in 8..20) return false
    val lowerCaseRegex = Regex("[a-z]")
    val upperCaseRegex = Regex("[A-Z]")
    val digitRegex = Regex("[0-9]")
    val specialRegex = Regex("[!@#\$%^&*(),.?\\\":{}|<>]")

    val criteriaCnt = listOf(
        lowerCaseRegex.containsMatchIn(password),
        upperCaseRegex.containsMatchIn(password),
        digitRegex.containsMatchIn(password),
        specialRegex.containsMatchIn(password)
    ).count { it }

    return criteriaCnt >= 3
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    ANDANDROIDTheme {
        SignUp(onSignUpSuccess = { _, _ -> }, onSignUpFailure = {})
    }
}