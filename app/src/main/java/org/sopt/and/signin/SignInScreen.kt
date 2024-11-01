package org.sopt.and.signin

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.component.AuthSignButton
import org.sopt.and.viewmodel.SignViewModel

import org.sopt.and.component.CustomTextField

@Composable
fun SignInScreen(signViewModel: SignViewModel, onNavigateToMain: () -> Unit) {
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
            LoginHeader()

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
                snackbarHostState = snackbarHostState,
                onSuccess = {
                    onNavigateToMain()
                },
                onFailure = {}
            )
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