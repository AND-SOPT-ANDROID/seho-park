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
import org.sopt.and.presentation.myinfo.MyInfoScreen
import org.sopt.and.presentation.myinfo.MyInfoViewModel
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.signin.SignInScreen
import org.sopt.and.presentation.signup.SignUpScreen
import org.sopt.and.presentation.viewmodelfactory.MyInfoViewModelFactory
import org.sopt.and.ui.theme.WavveBg

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
                BottomNavigation(
                    items = navigationUiState.BottomNavigationItems,
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
                    navigateToSignUp = { navController.navigate(route = Routes.SignUp) },
                    navigateToMyInfo = {
                        navigationViewModel.changeBottomNavigationVisibility()
                        navController.navigate(Routes.MyInfo)
                    }
                )
            }

            composable<Routes.SignUp> {
                SignUpScreen(
                    navigateToSignIn = {
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
                MyInfoScreen(
                    paddingValues = innerPadding,
                    myHobby = myInfoUiState.myHobby,
                    getMyHobby = myInfoViewModel::getMyHobby
                )
            }

            composable<Routes.Home> {
                HomeScreen(
                    innerPadding = innerPadding
                )
            }

            composable<Routes.Search> {
                SearchScreen(Modifier.Companion.background(WavveBg))
            }
        }
    }
}