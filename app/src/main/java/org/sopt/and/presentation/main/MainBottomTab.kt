package org.sopt.and.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.and.R
import org.sopt.and.core.navigation.Screen

data class MainBottomTab(
    val screen: Screen,
    @DrawableRes val iconResId: Int,
    @StringRes val labelResId: Int,
    val isProfileImage: Boolean = false
)

object MainBottomTabs {
    val Home = MainBottomTab(
        screen = Screen.Home,
        iconResId = R.drawable.ic_home,
        labelResId = R.string.title_home
    )
    val Search = MainBottomTab(
        screen = Screen.Search,
        iconResId = R.drawable.ic_search,
        labelResId = R.string.title_search
    )
    val MyPage = MainBottomTab(
        screen = Screen.My,
        iconResId = R.drawable.profile_default,
        labelResId = R.string.title_my,
        isProfileImage = true
    )

    val items = listOf(Home, Search, MyPage)
}