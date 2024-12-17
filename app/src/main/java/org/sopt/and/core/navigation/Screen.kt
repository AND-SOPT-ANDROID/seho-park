package org.sopt.and.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object SignIn: Screen()

    @Serializable
    data object SignUp : Screen()

    @Serializable
    data object My: Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Search : Screen()
}