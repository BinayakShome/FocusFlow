package com.example.focusflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    //val loginViewModel: LoginViewModel = viewModel()
    //val isUserLoggedIn by rememberUpdatedState(Firebase.auth.currentUser != null)
    //val startDestination = if (isUserLoggedIn) "main" else "login_graph"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginNavGraph(navController, loginViewModel = loginViewModel)
        mainNavGraph(navController)
    }
}