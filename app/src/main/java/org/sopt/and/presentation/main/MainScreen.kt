package org.sopt.and.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import org.sopt.and.core.navigation.Screen
import org.sopt.and.core.utils.SnackBarUtils
import org.sopt.and.ui.theme.BottomNavigationItemUnselected
import org.sopt.and.ui.theme.White

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = White,
        unselectedIconColor = BottomNavigationItemUnselected,
        selectedTextColor = White,
        unselectedTextColor = BottomNavigationItemUnselected,
        indicatorColor = Color.Transparent
    )

    LaunchedEffect(Unit) {
        viewModel.sendEvent(MainContract.MainUiEvent.LoadUserToken)
    }
    val startDestination = if (mainState.userToken.isNullOrBlank()) Screen.SignIn else Screen.My

    var currentRoute by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            currentRoute = backStackEntry.destination.route
        }
    }

    val bottomBarScreens = listOf(
        Screen.Home.javaClass.canonicalName,
        Screen.Search.javaClass.canonicalName,
        Screen.My.javaClass.canonicalName
    )
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        SnackBarUtils.init(snackbarHostState)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            if (currentRoute in bottomBarScreens) {
                MainBottomNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    colors = colors
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainNavHost(
                navController = navController,
                startDestination = startDestination
            )
        }
    }
}
