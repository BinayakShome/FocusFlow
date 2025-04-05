package com.example.focusflow.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.focusflow.viewmodel.HomeViewModel
import com.example.focusflow.viewmodel.ProfileViewModel
import com.example.focusflow.views.screen.HomeScreen
import com.example.focusflow.views.screen.ProfileScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.HomeScreen.route,
        route = "main"
    ) {

        composable(Screen.HomeScreen.route){
            val homeViewModel: HomeViewModel = viewModel()

            HomeScreen(navController = navController,
                viewModel = homeViewModel
            )
        }
        composable(Screen.ProfileScreen.route){
            val profileViewModel: ProfileViewModel = viewModel()

            ProfileScreen(navController = navController,
                viewModel = profileViewModel)
        }
    }
}