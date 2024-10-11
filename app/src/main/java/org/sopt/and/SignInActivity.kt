package org.sopt.and

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignIn()
            }
        }
    }
}

@Composable
fun SignInHeader() {
    Spacer(modifier = Modifier.height(20.dp))
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
            text = "Wavve",
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SignInView() {
    // 상태 변수 정의
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        // 아이디 입력 필드
        Spacer(modifier = Modifier.height(50.dp))

        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "이메일 주소 또는 아이디", color = Color.DarkGray) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        // 비밀번호 입력 필드
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "비밀번호", color = Color.DarkGray) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                unfocusedContainerColor = Color.Gray
            ),
            placeholder = { Text("Wavve 비밀번호 설정") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Text(text = if (isPasswordVisible) "Hide" else "Show", color = Color.White)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // 로그인 버튼
        Button(
            onClick = { /* 로그인 로직 추가 */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "로그인", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun SignIn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SignInHeader()
        SignInView()
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    ANDANDROIDTheme {
        SignIn()
    }
}