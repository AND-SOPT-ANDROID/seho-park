package org.sopt.and.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object MyInfo : Routes()

    @Serializable
    object SignIn : Routes()

    @Serializable
    object SignUp : Routes()

    @Serializable
    object Home : Routes()

    @Serializable
    object Search : Routes()
}