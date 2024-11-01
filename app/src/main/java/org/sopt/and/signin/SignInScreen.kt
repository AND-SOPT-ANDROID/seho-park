package org.sopt.and.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.viewmodel.SignViewModel


@Composable
fun SignInScreen(signViewModel: SignViewModel, onNavigateToMain: ()-> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(15.dp)
        ) {
            LoginHeader()

            Spacer(modifier = Modifier.height(10.dp))

            // 이메일 입력 필드
            TextField(
                value = signViewModel.email,
                onValueChange = { signViewModel.email = it },
                label = { Text(text = stringResource(id = R.string.email_label)) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Gray,
                    unfocusedContainerColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 비밀번호 입력 필드 및 show/hide 버튼
            PasswordField(
                password = signViewModel.password,
                isPasswordVisible = isPasswordVisible,
                onPasswordChange = { signViewModel.password = it },
                onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 로그인 버튼
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (signViewModel.validateSignIn()) {
                            snackbarHostState.showSnackbar("로그인 성공!")
                            onNavigateToMain()
                        } else {
                            snackbarHostState.showSnackbar("로그인 실패: 이메일과 비밀번호를 확인해주세요.")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(
                    text = stringResource(id = R.string.login_label),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun LoginHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Text(
            text = "<",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = "Wavve",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PasswordField(
    password: String,
    isPasswordVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onVisibilityToggle: () -> Unit
) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = stringResource(id = R.string.password_label)) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.Gray
        ),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            TextButton(onClick = onVisibilityToggle) {
                Text(
                    text = if (isPasswordVisible) "Hide" else "Show",
                    color = Color.White
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}