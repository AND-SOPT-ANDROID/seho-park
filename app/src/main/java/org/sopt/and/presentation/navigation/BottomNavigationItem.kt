package org.sopt.and.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label: Int,
    val icon: ImageVector,
    val route: Routes,
    val index: Int
)