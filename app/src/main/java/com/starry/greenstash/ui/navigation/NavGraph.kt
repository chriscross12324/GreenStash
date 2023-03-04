package com.starry.greenstash.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.starry.greenstash.ui.screens.home.composables.HomeScreen
import com.starry.greenstash.ui.screens.input.composables.InputScreen
import com.starry.greenstash.ui.screens.settings.composables.SettingsScreen

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    AnimatedNavHost(
        navController = navController,
        startDestination = DrawerScreens.Home.route,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ) {
        /** Home Screen */
        composable(
            route = DrawerScreens.Home.route,
        ) {
            HomeScreen(navController)
        }

        /** Input Screen */
        composable(
            route = Screens.InputScreen.route,
            arguments = listOf(navArgument(EDIT_GOAL_ARG_KEY) {
                nullable = true
                defaultValue = null
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val editGoalId = backStackEntry.arguments!!.getString(EDIT_GOAL_ARG_KEY)
            InputScreen(editGoalId, navController)
        }

        /** Settings Screen */
        composable(
            route = DrawerScreens.Settings.route
        ) {
            SettingsScreen(navController)
        }

    }
}