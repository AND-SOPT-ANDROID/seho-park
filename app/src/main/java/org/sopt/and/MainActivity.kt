package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.sopt.and.data.service.AppContext
import org.sopt.and.presentation.navigation.Navigation
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContext.init(this)

        setContent {
            ANDANDROIDTheme {
                Navigation()
            }
        }
    }
}