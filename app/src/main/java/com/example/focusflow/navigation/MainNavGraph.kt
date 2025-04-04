package com.example.focusflow.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.HomeScreen.route,
        route = "main"
    ) {

//        composable(Screen.HomeScreen.route){
//            val homeScreenViewModel: HomeScreenViewModel = viewModel()
//
//            HomeScreen(navController = navController,
//                viewModel = homeScreenViewModel)
//        }
    }
}