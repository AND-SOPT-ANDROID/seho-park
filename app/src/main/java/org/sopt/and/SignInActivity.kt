package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
//                SignIn()
            }
        }
    }
}