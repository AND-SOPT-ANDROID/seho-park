package org.sopt.and.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Screen
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.myinfo.MyInfo
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.signin.SignInScreen
import org.sopt.and.presentation.signup.SignUpScreen
import org.sopt.and.ui.theme.WavveBg

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<Screen.SignIn> {
            SignInScreen(
                navigateToMy = {
                    navController.navigate(Screen.My)
                },
                navigateToSignUp = {
                    navController.navigate(Screen.SignUp)
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.SignUp> {
            SignUpScreen(
                navigateToSignIn = {
                    navController.navigate(Screen.SignIn) {
                        popUpTo<Screen.SignUp> { inclusive = true }
                        launchSingleTop = true
                    }
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.My> {
            MyInfo(
                navigateToSignIn = {
                    navController.navigate(Screen.SignIn) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Screen.Home> {
            HomeScreen(
                onContentTypeSelected = { /* TODO: Screen 변경 가능 */ },
                modifier = Modifier.background(WavveBg)
            )
        }
        composable<Screen.Search> {
            SearchScreen(modifier = Modifier.background(WavveBg))
        }
    }
}
