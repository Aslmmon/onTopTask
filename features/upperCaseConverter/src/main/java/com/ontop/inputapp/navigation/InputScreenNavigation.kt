package com.ontop.inputapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ontop.inputapp.InputScreen

const val UPPER_CASE_CONVERTER_ROUTE = "upperCaseRoute"

fun NavController.navigateToUpperCaseConverterScreen(navOptions: NavOptions? = null) {
    val route = UPPER_CASE_CONVERTER_ROUTE
    navigate(route, navOptions)
}

fun NavGraphBuilder.upperCaseConverterScreen() {
    composable(
        route = UPPER_CASE_CONVERTER_ROUTE,
    ) {
        InputScreen()
    }
}
