package org.sopt.and.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey100
import org.sopt.and.ui.theme.Grey200

@Composable
fun WavveBottomNavigation(
    items: List<BottomNavigationItem>,
    navController: NavController,
    setNavigationSelectedScreenIndex: (Int) -> Unit,
    navigationSelectedScreenIndex: Int
) {
    NavigationBar(
        modifier = Modifier.height(60.dp),
        containerColor = Color.Black
    ) {
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = index == navigationSelectedScreenIndex,
                onClick = {
                    setNavigationSelectedScreenIndex(index)
                    navController.navigate(
                        bottomNavigationItem.route,
                        navOptions = navOptions {
                            launchSingleTop
                        }
                    )
                },
                icon = {
                    Icon(
                        imageVector = bottomNavigationItem.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottomNavigationItem.label),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Grey200,
                    unselectedTextColor = Grey200,
                    disabledIconColor = Grey100,
                    disabledTextColor = Grey100
                )
            )
        }

    }
}

@Preview
@Composable
fun WavveBottomNavigationPreview() {
    val index = remember { mutableIntStateOf(0) }
    WavveBottomNavigation(
        listOf(
            BottomNavigationItem(
                label = R.string.bottom_navigation_home_label,
                icon = Icons.Default.Home,
                route = Routes.Home,
                index = 0
            ),
            BottomNavigationItem(
                label = R.string.bottom_navigation_search_label,
                icon = Icons.Default.Search,
                route = Routes.Search,
                index = 1
            ),
            BottomNavigationItem(
                label = R.string.bottom_navigation_my_info_label,
                icon = Icons.Default.AccountCircle,
                route = Routes.MyInfo,
                index = 2
            )
        ),
        navController = rememberNavController(),
        setNavigationSelectedScreenIndex = { index.intValue = it },
        navigationSelectedScreenIndex = index.intValue,
    )
}