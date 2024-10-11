package org.sopt.and

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val email = intent.getStringExtra("email") ?: ""
                val password = intent.getStringExtra("password") ?: ""
                SignIn(email, password)
            }
        }
    }
}

@Composable
fun LoginHeader(){
    Spacer(modifier = Modifier.height(80.dp))
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "<",
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Wavve",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SignIn(registeredEmail: String, registeredPassword: String) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) } // 비밀번호 가시성 상태 변수
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(15.dp)
        ) {
            LoginHeader()

            // 아이디 입력 필드
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "이메일 주소 또는 아이디") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 비밀번호 입력 필드
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "비밀번호") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Gray
                ),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(), // 비밀번호 가시성 설정
                trailingIcon = {
                    TextButton(onClick = {
                        isPasswordVisible = !isPasswordVisible // 가시성 토글
                    }) {
                        Text(text = if (isPasswordVisible) "Hide" else "Show", color = Color.White)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 로그인 버튼
            Button(
                onClick = {
                    scope.launch {
                        if (email == registeredEmail && password == registeredPassword) {
                            snackbarHostState.showSnackbar("로그인 성공!")

                            val intent = Intent(context, MyActivity::class.java).apply {
                                putExtra("email", email)  // email 정보 전달
                            }
                        } else {
                            snackbarHostState.showSnackbar("로그인 실패: 이메일과 비밀번호를 확인해주세요.")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "로그인", color = Color.White)
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween // 좌우로 균등하게 배치
            ) {
                Text(
                    text = "아이디 찾기",
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "|",
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "비밀번호 재설정",
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "|",
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "회원가입",
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    ANDANDROIDTheme {
        SignIn("example@example.com", "password123")
    }
}