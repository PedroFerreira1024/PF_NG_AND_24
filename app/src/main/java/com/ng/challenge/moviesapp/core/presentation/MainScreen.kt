package com.ng.challenge.moviesapp.core.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ng.challenge.moviesapp.core.presentation.navigation.BottomNavigationBar
import com.ng.challenge.moviesapp.core.presentation.navigation.DetailScreenNav
import com.ng.challenge.moviesapp.core.presentation.navigation.NavigationGraph
import com.ng.challenge.moviesapp.core.presentation.navigation.currentRoute


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier,
               navController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Scaffold(modifier = modifier.systemBarsPadding(),
            bottomBar = {
                if (
                    currentRoute(navController = navController) != DetailScreenNav.DetailScreen.route
                )
                    BottomNavigationBar(navController = navController)
            },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    NavigationGraph(navController = navController)
                }
            }
        )
    }
}