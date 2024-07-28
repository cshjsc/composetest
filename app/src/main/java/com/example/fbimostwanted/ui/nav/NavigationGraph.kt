package com.example.fbimostwanted.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.fbimostwanted.Home
import com.example.fbimostwanted.HomeScreen
import com.example.fbimostwanted.PersonItem
import com.example.fbimostwanted.PersonUIItem

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController,
        startDestination = Home,
        modifier = modifier
        ){
        composable<Home>{
            HomeScreen(navigateToDetails = {
                navController.navigate(it)
            })
        }
        composable<PersonItem> { navBackStackEntry ->
            val personItem: PersonItem = navBackStackEntry.toRoute()
            PersonUIItem(personItem = personItem, navigateToDetails = {})
        }
    }
}