package com.weatherapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weatherapp.ui.HomePage
import com.weatherapp.ui.ListPage
import com.weatherapp.ui.MapPage
import com.weatherapp.ui.MainViewModel
import android.content.Context

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    context: Context
) {
    val cityList = viewModel.cities
    NavHost(navController, startDestination = BottomNavItem.HomePage.route) {
        composable(route = BottomNavItem.HomePage.route) {
            HomePage(viewModel = viewModel, context = context)
        }
        composable(route = BottomNavItem.ListPage.route) {
            ListPage(viewModel = viewModel, context = context)
        }
        composable(route = BottomNavItem.MapPage.route) {
            MapPage(viewModel = viewModel, context = context)
        }
    }
}