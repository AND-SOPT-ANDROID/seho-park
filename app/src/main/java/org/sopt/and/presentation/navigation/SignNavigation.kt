package org.sopt.and.presentation.navigation


sealed class SignNavigation(val route: String) {
    object SignUp : SignNavigation("signUp")
    object SignIn : SignNavigation("signIn")
    object Main : SignNavigation("main")
}