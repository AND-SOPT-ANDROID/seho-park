package org.sopt.and.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import org.sopt.and.R
import org.sopt.and.presentation.util.WavveUtils

data class NavigationUiState(
    val isBottomNavigationVisible: Boolean = false,
    val navigationSelectedIndex: Int = WavveUtils.MYINFO_SCREEN_INDEX,
    val wavveBottomNavigationItems: List<WavveBottomNavigationItem> = listOf(
        WavveBottomNavigationItem(
            label = R.string.bottom_navigation_home_label,
            icon = Icons.Default.Home,
            route = Routes.Home,
            index = 0
        ),
        WavveBottomNavigationItem(
            label = R.string.bottom_navigation_search_label,
            icon = Icons.Default.Search,
            route = Routes.Search,
            index = 1
        ),
        WavveBottomNavigationItem(
            label = R.string.bottom_navigation_my_info_label,
            icon = Icons.Default.AccountCircle,
            route = Routes.MyInfo,
            index = 2
        )
    )
)