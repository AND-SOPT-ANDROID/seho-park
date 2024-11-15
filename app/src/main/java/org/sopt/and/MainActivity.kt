package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.navigation.SignNavigation
import org.sopt.and.signup.SignUpScreen

import org.sopt.and.signin.SignInScreen

import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.viewmodel.SignViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val signViewModel: SignViewModel = viewModel()
                MyApp(signViewModel)
            }
        }
    }
}

@Composable
fun MyApp(signViewModel: SignViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = SignNavigation.SignUp.route) {
        composable(SignNavigation.SignUp.route) {
            SignUpScreen(
                signViewModel = signViewModel,
//                onNavigateBack = { navController.popBackStack() },
                onNavigateToSignIn = { navController.navigate("signIn") },
            )
        }
        composable(SignNavigation.SignIn.route) {
            SignInScreen(
                signViewModel = signViewModel,
                onNavigateToMain = {navController.navigate("main"){
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                }},
                onNavigateToSignUp = {navController.navigate("signUp")}

            )
        }

        composable(SignNavigation.Main.route) {
            MainScreen(signViewModel = signViewModel)
        }
    }
}

