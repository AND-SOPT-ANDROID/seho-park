package org.sopt.and.myinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyScreen(modifier: Modifier = Modifier, email: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MyHeader(email)
        PurchseZone("첫 결재 시 첫 달 100원!")
        Spacer(modifier = Modifier.height(1.dp))
        PurchseZone("현재 보유하신 이용권이 없습니다.")

        InfoZone("전체 시청내역", "시청 내역이 없어요.")
        Spacer(modifier = Modifier.height(30.dp))
        InfoZone("관심 프로그램", "관심 프로그램이 없어요.")
        Spacer(modifier = Modifier.weight(1f))
        Footer()
    }
}

@Composable
fun MyHeader(email: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.DarkGray)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = email,
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PurchseZone(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 15.sp, color = Color.LightGray)
    }
}

@Composable
fun InfoZone(title: String, message: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = message, color = Color.Gray)
    }
}

@Composable
fun Footer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(imageVector = Icons.Default.Home, contentDescription = "홈 아이콘", tint = Color.White)
        Icon(imageVector = Icons.Default.Search, contentDescription = "검색 아이콘", tint = Color.White)
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "프로필 아이콘", tint = Color.White)
    }
}