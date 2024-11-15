package org.sopt.and.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.components.AuthSignButton
import org.sopt.and.viewmodel.SignViewModel
import org.sopt.and.components.CustomTextField
import org.sopt.and.components.SignTopBar


@Composable
fun SignUpScreen(
    signViewModel: SignViewModel,
    onNavigateToSignIn: () -> Unit
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

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
            SignUpHeader(onNavigateToSignIn)
            Spacer(modifier = Modifier.height(20.dp))
            SignTopBar(isSignUp = true)
            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(
                labelResId = R.string.email_label,
                textValue = signViewModel.email,
                onTextChanged = { signViewModel.email = it },
                showHint = true,
                hintResId = R.string.sign_up_id,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                labelResId = R.string.password_label,
                textValue = signViewModel.password,
                onTextChanged = { signViewModel.password = it },
                isPasswordField = true,
                isPasswordVisible = signViewModel.isPasswordVisible,
                onPasswordToggle = {
                    signViewModel.isPasswordVisible = !signViewModel.isPasswordVisible
                },
                showHint = true,
                hintResId = R.string.sign_up_passwd,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            AuthSignButton(
                buttonText = "Wavve 회원가입",
                validateAction = { signViewModel.validateSignInOrUp() },
                onSuccess = {
                    signViewModel.performSignUp()
                    Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                    onNavigateToSignIn()
                },
                onFailure = {
                    Toast.makeText(context, "회원가입 실패: 입력 정보를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            )
        }
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


