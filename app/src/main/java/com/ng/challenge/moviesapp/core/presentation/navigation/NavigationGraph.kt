package com.ng.challenge.moviesapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(modifier: Modifier = Modifier,
                    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route)
    {
        composable(BottomNavItem.MoviePopular.route) {

        }
        composable(BottomNavItem.MovieSearch.route) {

        }
    }
}