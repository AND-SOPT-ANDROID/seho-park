package org.sopt.and.navigation


sealed class SignNavigation(val route: String) {
    object SignUp : SignNavigation("signUp")
    object SignIn : SignNavigation("signIn")
    object Main : SignNavigation("main")
}