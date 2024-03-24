package com.ontop.startscreen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ontop.startscreen.ui.Home


const val HOME_ROUTE = "home"

fun NavGraphBuilder.home(
    onNavigateToMoviesApp: () -> Unit,
    onNavigateToInput: () -> Unit,
    onNavigateToPasswordGenerator: () -> Unit
) {
    composable(
        route = HOME_ROUTE,
    ) {
        Home(onNavigateToMoviesApp, onNavigateToInput, onNavigateToPasswordGenerator)
    }
}
