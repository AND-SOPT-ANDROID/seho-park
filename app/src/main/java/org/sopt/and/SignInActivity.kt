package org.sopt.and

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    // signUpLauncher를 통해 결과 값을 받아 처리
    val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val email = result.data?.getStringExtra("email") ?: ""
            val password = result.data?.getStringExtra("password") ?: ""
            setContent {
                ANDANDROIDTheme {
                    // 상태 변경 반영
                    SignIn(email, password)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val email = intent.getStringExtra("email").orEmpty()
                val password = intent.getStringExtra("password").orEmpty()
                SignIn(email, password)
            }
        }
    }
}


@Composable
fun LoginHeader(){
    val context = LocalContext.current as? Activity
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
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable {
                    context?.finish()
                }
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
    var email by remember { mutableStateOf(registeredEmail) }
    var password by remember { mutableStateOf(registeredPassword) }
    var isPasswordVisible by remember { mutableStateOf(false) } // 비밀번호 가시성 상태 변수
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current as? Activity

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
                label = { Text(text = stringResource(id = R.string.email_label))},
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
                label = { Text(text = stringResource(id = R.string.password_label)) },
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
            Button(
                onClick = {
                    scope.launch {
                        if (email == registeredEmail && password == registeredPassword) {
                            snackbarHostState.showSnackbar("로그인 성공!")
                            val intent = Intent(context, MyActivity::class.java).apply {
                                putExtra("email", email)  // email 정보 전달
                            }
                            context?.startActivity(intent)  // Activity 시작

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
                Text(text = stringResource(id = R.string.login_label), color = Color.White)
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween // 좌우로 균등하게 배치
            ) {
                Text(
                    text = stringResource(id = R.string.find_id_label),
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.separator),
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.reset_pw_label),
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.separator),
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.sign_up_label),
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, SignUpActivity::class.java)
                        (context as SignInActivity).signUpLauncher.launch(intent)
                    }
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