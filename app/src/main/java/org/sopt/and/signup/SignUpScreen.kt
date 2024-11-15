package org.sopt.and.signup

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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
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
    val coroutineScope = rememberCoroutineScope()

    // 상태값 관리
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var hobby by remember { mutableStateOf(TextFieldValue("")) }

    var usernameError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var hobbyError by remember { mutableStateOf(false) }

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
            SignUpTobBar (onNavigateToSignIn)
            Spacer(modifier = Modifier.height(20.dp))
            SignTopBar(isSignUp = true)
            Spacer(modifier = Modifier.height(30.dp))

            // Username 입력 필드
            CustomTextField(
                labelResId = R.string.username_label,
                textValue = username,
                onTextChanged = {
                    username = it
                    usernameError = username.text.length < 8
                },
                showHint = true,
                hintResId = R.string.sign_up_username_hint,
                modifier = Modifier.fillMaxWidth()
            )
            if (usernameError) {
                Text(
                    text = "Username must be at least 8 characters.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Password 입력 필드
            CustomTextField(
                labelResId = R.string.password_label,
                textValue = password,
                onTextChanged = {
                    password = it
                    passwordError = password.text.length < 8
                },
                isPasswordField = true,
                isPasswordVisible = signViewModel.isPasswordVisible,
                onPasswordToggle = {
                    signViewModel.isPasswordVisible = !signViewModel.isPasswordVisible
                },
                showHint = true,
                hintResId = R.string.sign_up_password_hint,
                modifier = Modifier.fillMaxWidth()
            )
            if (passwordError) {
                Text(
                    text = "Password must be at least 8 characters.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Hobby 입력 필드
            CustomTextField(
                labelResId = R.string.hobby_label,
                textValue = hobby,
                onTextChanged = {
                    hobby = it
                    hobbyError = hobby.text.length < 8
                },
                showHint = true,
                hintResId = R.string.sign_up_hobby_hint,
                modifier = Modifier.fillMaxWidth()
            )
            if (hobbyError) {
                Text(
                    text = "Hobby must be at least 8 characters.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // 회원가입 버튼
            AuthSignButton(
                buttonText = "회원가입",
                validateAction = {
                    // 전체 유효성 검사
                    usernameError = username.text.length < 8
                    passwordError = password.text.length < 8
                    hobbyError = hobby.text.length < 8

                    // 모든 조건을 만족해야 회원가입 요청 실행
                    !usernameError && !passwordError && !hobbyError
                },
                onSuccess = {
                    signViewModel.performSignUp(
                        username.text,
                        password.text,
                        hobby.text,
                        onSuccess = {
                            Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            onNavigateToSignIn()
                        },
                        onFailure = { errorMessage ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(errorMessage)
                            }
                        }
                    )
                },
                onFailure = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("입력 값을 확인해주세요.")
                    }
                }
            )
        }
    }
}

@Composable
fun SignUpTobBar(onNavigateToSignIn: () -> Unit) {
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


