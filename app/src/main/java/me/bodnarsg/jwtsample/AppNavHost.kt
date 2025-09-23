package me.bodnarsg.jwtsample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import me.bodnarsg.jwtsample.presentation.auth.registration.RegistrationView

@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String)
{
    NavHost(navController = navHostController, startDestination = startDestination){
        navigation(startDestination = "auth/registration", route = "auth"){
            composable("auth/registration"){
                RegistrationView()
            }
            composable("auth/login"){
                //TODO: Create LoginScreen
            }
            composable("auth/forgot_password") {
                //TODO: Create ForgotPasswordScreen
            }
        }
    }
}