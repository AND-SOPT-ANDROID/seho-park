package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val email = intent.getStringExtra("email") ?: "test@test.test"
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyPage(modifier = Modifier.padding(innerPadding), email = email)
                }
            }
        }
    }
}

@Composable
fun MyPage(modifier: Modifier = Modifier, email: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.DarkGray)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Yellow, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))

                // 회원가입 시 입력된 이메일 출력
                Text(
                    text = email, // 전달된 이메일을 닉네임으로 출력
                    fontSize = 15.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "알림",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                    imageVector = Icons.Default.Settings,
                    contentDescription = "설정",
                    tint = Color.White
                )
            }
        }


        PurchseZone("첫 결재 시 첫 달 100원!")
        Spacer(modifier = Modifier.height(1.dp))
        PurchseZone ("현재 보유하신 이용권이 없습니다.")

        InfoZone("전체 시청내역", "시청 내역이 없어요.")
        Spacer(modifier = Modifier.height(30.dp))
        InfoZone("관심 프로그램", "관심 프로그램이 없어요.")

    }
}

@Composable
fun PurchseZone(title: String, ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(10.dp)
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            color = Color.LightGray
        )
        Text(
            text = "구매하기 >",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}


@Composable
fun InfoZone(text: String, message: String) {
    Text(
        modifier = Modifier.padding(15.dp),
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(80.dp)
                .clickable { },
            imageVector = Icons.Filled.Warning,
            contentDescription = "알림",
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier,
            text = message,
            color = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    MyPage(email = "323psh@naver.com")
}