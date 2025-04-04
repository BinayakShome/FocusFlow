package com.example.focusflow.navigation

sealed class Screen(val route: String){
    object LoginScreen: Screen("LoginScreen")
    object HomeScreen: Screen("HomeScreen")
}