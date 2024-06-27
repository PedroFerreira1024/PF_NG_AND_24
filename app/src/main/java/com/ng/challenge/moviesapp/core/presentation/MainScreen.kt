package com.ng.challenge.moviesapp.core.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ng.challenge.moviesapp.core.presentation.navigation.BottomNavigationBar
import com.ng.challenge.moviesapp.core.presentation.navigation.NavigationGraph


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier,
               navController: NavHostController
) {
    Scaffold(modifier = modifier.systemBarsPadding(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = {
            NavigationGraph(navController = navController)
        }
    )
}