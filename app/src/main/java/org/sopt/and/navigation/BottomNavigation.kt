package org.sopt.and.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.R

enum class BottomNavigation(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
    Home(
        route = "Home",
        title = R.string.home_label,
        icon = Icons.Default.Home
    ),
    Search(
        route = "Search",
        title = R.string.search_label,
        icon = Icons.Default.Search
    ),
    MY(
        route = "MY",
        title = R.string.my_label,
        icon = Icons.Default.AccountCircle
    );
}