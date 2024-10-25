package org.sopt.and.signin

import android.app.Activity
import android.content.Intent
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignInScreen(registeredEmail: String = "", registeredPassword: String = "") {
    val context = LocalContext.current as? Activity
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf(registeredEmail) }
    var password by remember { mutableStateOf(registeredPassword) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(15.dp)
        ) {
            LoginHeader(context)

            Spacer(modifier = Modifier.height(10.dp))

            // 아이디 입력 필드
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(id = R.string.email_label)) },
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

            // 비밀번호 입력 필드 및 show/hide 버튼
            PasswordField(password, isPasswordVisible) {
                password = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 로그인 버튼
            LoginButton(
                email = email,
                password = password,
                registeredEmail = registeredEmail,
                registeredPassword = registeredPassword,
                snackbarHostState = snackbarHostState,
                context = context
            )

            Spacer(modifier = Modifier.height(5.dp))

            // 하단 링크
            BottomLinks(context)
        }
    }
}

@Composable
fun LoginHeader(context: Activity?) {
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
                .clickable { context?.finish() }
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
fun PasswordField(password: String, isPasswordVisible: Boolean, onValueChange: (String) -> Unit) {
    var isVisible by remember { mutableStateOf(isPasswordVisible) }
    TextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.password_label)) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.Gray
        ),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            TextButton(onClick = { isVisible = !isVisible }) {
                Text(text = if (isVisible) "Hide" else "Show", color = Color.White)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LoginButton(
    email: String,
    password: String,
    registeredEmail: String,
    registeredPassword: String,
    snackbarHostState: SnackbarHostState,
    context: Activity?
) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                if (email == registeredEmail && password == registeredPassword) {
                    snackbarHostState.showSnackbar("로그인 성공!")
                    val intent = Intent(context, MyActivity::class.java).apply {
                        putExtra("email", email)
                    }
                    context?.startActivity(intent)
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
}

@Composable
fun BottomLinks(context: Activity?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextLink(stringResource(id = R.string.find_id_label))
        Text(text = "|", fontSize = 15.sp, color = Color.White)
        TextLink(stringResource(id = R.string.reset_pw_label))
        Text(text = "|", fontSize = 15.sp, color = Color.White)
        TextLink(
            stringResource(id = R.string.sign_up_label),
            modifier = Modifier.clickable {
                val intent = Intent(context, SignUpActivity::class.java)
                context?.startActivity(intent)
            }
        )
    }
}

@Composable
fun TextLink(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 15.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}