package org.sopt.and.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignTopBar(isSignUp: Boolean) {
    if (isSignUp) {
        SignUpText()
    } else {
        SignInText()
    }
}

@Composable
fun SignUpText() {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
            append("이메일과 비밀번호")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
            append("만으로\n")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
            append("Wavve를 즐길 수 ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
            append("있어요!")
        }
    }

    Text(
        text = annotatedText,
        color = Color.White,
        fontSize = 28.sp,
        modifier = Modifier.padding(start = 15.dp, end = 15.dp)
    )
}

@Composable
fun SignInText() {
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