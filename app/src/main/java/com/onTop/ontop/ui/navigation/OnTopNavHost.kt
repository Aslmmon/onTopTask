@file:Suppress("UNUSED_EXPRESSION")

package com.onTop.ontop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ontop.inputapp.navigation.upperCaseConverterScreen
import com.ontop.inputapp.navigation.navigateToUpperCaseConverterScreen
import com.ontop.moviesapp.navigation.detailScreen
import com.ontop.moviesapp.navigation.masterScreen
import com.ontop.moviesapp.navigation.navigateToDetailsScreen
import com.ontop.moviesapp.navigation.navigateToMasterScreen
import com.ontop.moviesapp.ui.SharedViewModel
import com.ontop.network.model.Movie
import com.ontop.passwordgenerator.navigation.navigateToPasswordGeneratorScreen
import com.ontop.passwordgenerator.navigation.passwordGeneratorScreen
import com.ontop.home.navigation.HOME_ROUTE
import com.ontop.home.navigation.home


@Composable
fun OnTopNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
    navController: NavHostController = rememberNavController()
) {
    val sharedViewModel = SharedViewModel<Movie>()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        home(
            onNavigateToMoviesApp = navController::navigateToMasterScreen,
            onNavigateToInput = navController::navigateToUpperCaseConverterScreen,
            onNavigateToPasswordGenerator = navController::navigateToPasswordGeneratorScreen,
        )
        masterScreen(
            sharedViewModel,
            onNavigateToDetailScreen = navController::navigateToDetailsScreen
        )
        detailScreen(sharedViewModel)
        upperCaseConverterScreen()
        passwordGeneratorScreen()

    }
}
