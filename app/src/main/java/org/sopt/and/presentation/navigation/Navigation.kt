package org.sopt.and.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.myinfo.MyInfoViewModel
import org.sopt.and.presentation.myinfo.MyScreen
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.signin.SignInScreen
import org.sopt.and.presentation.signup.SignUpScreen
import org.sopt.and.presentation.viewmodelfactory.MyInfoViewModelFactory


@Composable
fun Navigation(
) {
    val navigationViewModel = viewModel<NavigationViewModel>()
    val navigationUiState by navigationViewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    val myInfoViewModel: MyInfoViewModel = viewModel(
        factory = MyInfoViewModelFactory()
    )
    val myInfoUiState by myInfoViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (navigationUiState.isBottomNavigationVisible) {
                WavveBottomNavigation(
                    items = navigationUiState.wavveBottomNavigationItems,
                    navController = navController,
                    setNavigationSelectedScreenIndex = navigationViewModel::setNavigationSelectedIndex,
                    navigationSelectedScreenIndex = navigationUiState.navigationSelectedIndex
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SignIn
        ) {
            composable<Routes.SignIn> {
                SignInScreen(
                    onNavigateToSignUp = { navController.navigate(route = Routes.SignUp) },
                    signViewModel = ,
                    onNavigateToMain = {
                        navigationViewModel.changeBottomNavigationVisibility()
                        navController.navigate(Routes.MyInfo)
                    }
                )
            }

            composable<Routes.SignUp> {
                SignUpScreen(
                    signViewModel = ,
                    onNavigateToSignIn = {
                        navController.navigate(
                            route = Routes.SignIn,
                            navOptions = navOptions {
                                popUpTo<Routes.SignIn> {
                                    inclusive = true
                                }
                            }
                        )
                    }
                )
            }

            composable<Routes.MyInfo> {
                MyScreen(
                    paddingValues = innerPadding,
                    myHobby = myInfoUiState.myHobby,
                    getMyHobby = myInfoViewModel::getMyHobby,
                )
            }

            composable<Routes.Home> {
                HomeScreen(
                )
            }

            composable<Routes.Search> {
                SearchScreen()
            }
        }
    }
}