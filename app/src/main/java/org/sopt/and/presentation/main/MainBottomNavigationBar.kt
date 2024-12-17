package org.sopt.and.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.core.navigation.Screen
import org.sopt.and.ui.theme.WavveBg
import org.sopt.and.ui.theme.WavveDisabled


@Composable
fun MainBottomNavigationBar(
    navController: NavController,
    currentRoute: String?,
    colors: NavigationBarItemColors
) {
    BottomAppBar(
        containerColor = WavveBg,
        contentColor = WavveDisabled
    ) {
        MainBottomTabs.items.forEach { tab ->
            BottomNavigationItem(
                navController = navController,
                tab = tab,
                currentRoute = currentRoute,
                colors = colors
            )
        }
    }
}

@Composable
fun RowScope.BottomNavigationItem(
    navController: NavController,
    tab: MainBottomTab,
    currentRoute: String?,
    colors: NavigationBarItemColors
) {
    NavigationBarItem(
        icon = {
            if (tab.isProfileImage) {
                Image(
                    painter = painterResource(tab.iconResId),
                    contentDescription = stringResource(R.string.my_page_image_description_profile),
                    modifier = Modifier.size(32.dp)
                )
            } else {
                Icon(
                    ImageVector.vectorResource(tab.iconResId),
                    contentDescription = stringResource(tab.labelResId),
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        label = { Text(stringResource(tab.labelResId)) },
        selected = currentRoute == tab.screen.javaClass.canonicalName,
        onClick = { navigateToScreen(navController, tab.screen) },
        colors = colors
    )
}

private fun navigateToScreen(navController: NavController, screen: Screen) {
    screen.javaClass.canonicalName?.let {
        navController.navigate(it) {
            screen.javaClass.canonicalName?.let { it1 -> popUpTo(it1) { inclusive = false } }
            launchSingleTop = true
        }
    }
}
