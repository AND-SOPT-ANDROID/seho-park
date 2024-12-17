package org.sopt.and

import kotlinx.serialization.Serializable


@Serializable
sealed class Route(val route: String) {
    @Serializable
    data object Home : Route("home")

    @Serializable
    data object SignIn : Route("signIn")

    @Serializable
    data object SignUp : Route("signUp")

    @Serializable
    data object Search : Route("search")

    @Serializable
    data object MyInfo : Route("myInfo")
}