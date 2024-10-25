package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.navigation.AuthNavItem
import org.sopt.and.signup.SignUpScreen

import org.sopt.and.signin.SignInScreen

import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val signUpViewModel: SignUpViewModel = viewModel()
                MyApp(signUpViewModel)
            }
        }
    }
}

@Composable
fun MyApp(signUpViewModel: SignUpViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AuthNavItem.SignUp.route) {
        composable(AuthNavItem.SignUp.route) {
            SignUpScreen(signUpViewModel = signUpViewModel, navController = navController)
        }

        composable(AuthNavItem.SignIn.route) {
            SignInScreen(signUpViewModel = signUpViewModel, navController = navController)
        }

        composable(AuthNavItem.Main.route) {
            MainScreen(signUpViewModel = signUpViewModel)
        }

    }

}