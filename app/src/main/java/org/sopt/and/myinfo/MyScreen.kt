package org.sopt.and.myinfo

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.presentation.viewmodel.SignViewModel

enum class BottomNavItem(val icon: ImageVector, val description: String) {
    Home(Icons.Default.Home, "홈"),
    SEARCH(Icons.Default.Search, "검색"),
    PROFILE(Icons.Default.AccountCircle, "내 정보")
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyScreen(modifier: Modifier = Modifier, signViewModel: SignViewModel) {
    var hobby by mutableStateOf("") // 초기 값 확인
    val coroutineScope = rememberCoroutineScope()

    // 취미 데이터를 로드
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            signViewModel.fetchHobby(
                onSuccess = { /* 성공 시 처리할 로직 필요 없음 - 이미 상태가 업데이트됨 */ },
                onFailure = { errorMessage ->
                    Log.e("MyScreen", "취미 로드 실패: $errorMessage")
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MyHeader(hobby = hobby) // 최신 hobby 값을 전달
        Spacer(modifier = Modifier.height(20.dp))
        PurchseZone(title = "첫 결제 시 첫 달 100원!")
        Spacer(modifier = Modifier.height(15.dp))
        PurchseZone(title = "현재 보유하신 이용권이 없습니다.")
        Spacer(modifier = Modifier.height(20.dp))
        InfoZone(title = "전체 시청내역", message = "시청 내역이 없어요.")
        Spacer(modifier = Modifier.height(30.dp))
        InfoZone(title = "관심 프로그램", message = "관심 프로그램이 없어요.")
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigation()
    }
}

@Composable
fun MyHeader(hobby: String) {
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
            text = hobby.ifEmpty { "sport" }, // 초기 값 및 업데이트된 값 반영
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
fun BottomNavigation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavItem.entries.forEach { item ->
            Icon(
                imageVector = item.icon,
                contentDescription = item.description,
                tint = Color.White
            )
        }
    }
}