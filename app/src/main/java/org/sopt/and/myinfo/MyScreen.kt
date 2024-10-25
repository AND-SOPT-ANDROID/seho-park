package org.sopt.and.myinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.viewmodel.SignViewModel

@Composable
fun MyScreen(modifier: Modifier = Modifier, signViewModel: SignViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MyHeader(email = signViewModel.email)
        Spacer(modifier = Modifier.height(20.dp))
        PurchseZone(title = "첫 결제 시 첫 달 100원!")
        Spacer(modifier = Modifier.height(15.dp))
        PurchseZone(title = "현재 보유하신 이용권이 없습니다.")
        Spacer(modifier = Modifier.height(20.dp))
        InfoZone(title = "전체 시청내역", message = "시청 내역이 없어요.")
        Spacer(modifier = Modifier.height(30.dp))
        InfoZone(title = "관심 프로그램", message = "관심 프로그램이 없어요.")
        Spacer(modifier = Modifier.weight(1f))
        Footer()
    }
}

@Composable
fun MyHeader(email: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "프로필 이미지",
            modifier = Modifier.size(60.dp),
            tint = Color.White
        )
        Text(
            text = email,
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
        Row {
            Text(
                text = "🔔",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(end = 20.dp)
            )
            Text(
                text = "⚙️",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun PurchseZone(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(vertical = 15.dp, horizontal = 15.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.LightGray
        )
        Text(
            text = "구매하기 >",
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Composable
fun InfoZone(title: String, message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 23.sp,
            fontWeight = FontWeight.W800,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "⚠️",
            fontSize = 50.sp,
            color = Color.Gray
        )
        Text(
            text = message,
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 15.dp)
        )
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
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "홈 아이콘",
            tint = Color.White
        )
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "검색 아이콘",
            tint = Color.White
        )
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "프로필 아이콘",
            tint = Color.White
        )
    }
}