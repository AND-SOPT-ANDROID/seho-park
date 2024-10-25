package org.sopt.and.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.home.HomeScreen
import org.sopt.and.myinfo.MyScreen
import org.sopt.and.search.SearchScreen
import org.sopt.and.viewmodel.SignViewModel


@Composable
fun BottomNavigationGraph(navController: NavHostController, signViewModel: SignViewModel) {
    NavHost(navController = navController, startDestination = BottomNavigation.Home.route) {
        composable(route = BottomNavigation.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavigation.Search.route) {
            SearchScreen()
        }
        composable(route = BottomNavigation.MY.route) {
            MyScreen(signViewModel = signViewModel)
        }
    }
}