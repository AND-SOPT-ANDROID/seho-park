package org.sopt.and.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import org.sopt.and.R
import org.sopt.and.components.AuthSignButton
import org.sopt.and.viewmodel.SignViewModel

import org.sopt.and.components.CustomTextField
import org.sopt.and.components.SignTopBar

@Composable
fun SignInScreen(signViewModel: SignViewModel, onNavigateToMain: () -> Unit, onNavigateToSignUp: ()-> Unit)  {
    val snackbarHostState = remember { SnackbarHostState() }
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
            SignTopBar(isSignUp = false)
            Spacer(modifier = Modifier.height(10.dp))

            // 이메일 입력 필드
            CustomTextField(
                labelResId = R.string.email_label,
                textValue = signViewModel.email,
                onTextChanged = { signViewModel.email = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 비밀번호 입력 필드 및 show/hide 버튼
            CustomTextField(
                labelResId = R.string.password_label,
                textValue = signViewModel.password,
                onTextChanged = { signViewModel.password = it },
                isPasswordField = true,
                isPasswordVisible = isPasswordVisible,
                onPasswordToggle = { isPasswordVisible = !isPasswordVisible },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            AuthSignButton(
                buttonText = "로그인",
                validateAction = { signViewModel.validateSignIn() },
                onSuccess = {
                    onNavigateToMain()
                },
                onFailure = {}
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "아이디 찾기",
                    color = Color.White
                )

                Text(
                    text = " | ",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.White

                )

                Text(
                    text = "비밀번호 재설정",
                    color = Color.White

                )

                Text(
                    text = " | ",
                    color = Color.White

                )

                Text(
                    text = "회원가입",
                    modifier = Modifier.clickable(onClick = onNavigateToSignUp),
                    color = Color.White
                )
            }

        }
    }
}

