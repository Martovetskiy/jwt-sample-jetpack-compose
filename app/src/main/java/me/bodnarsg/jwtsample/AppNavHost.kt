package me.bodnarsg.jwtsample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import me.bodnarsg.jwtsample.navigation.Routes
import me.bodnarsg.jwtsample.presentation.auth.login.LoginView
import me.bodnarsg.jwtsample.presentation.auth.registration.RegistrationView
import me.bodnarsg.jwtsample.presentation.main.home.HomeView

@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        navigation(startDestination = Routes.REGISTER, route = Routes.AUTH_GRAPH) {
            composable(Routes.REGISTER) {
                RegistrationView(navController = navHostController)
            }
            composable(Routes.LOGIN) {
                LoginView(navController = navHostController)
            }
            composable("auth/forgot_password") { /* TODO */ }
        }
        navigation(startDestination = Routes.HOME, route = Routes.MAIN_GRAPH) {
            composable(Routes.HOME) {
                HomeView(navController = navHostController)
            }
        }
    }
}