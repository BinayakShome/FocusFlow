package com.example.focusflow.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

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