package com.example.focusflow.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.focusflow.views.screen.LoginScreen

fun NavGraphBuilder.loginNavGraph(
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = "login_graph"
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                state = loginViewModel.signInResult,
                onSignInSuccess = {
                    navController.navigate("main")
                },
                loginViewModel = loginViewModel
            )
        }
    }
}