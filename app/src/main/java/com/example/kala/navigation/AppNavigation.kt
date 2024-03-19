package com.example.kala.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kala.screens.AboutExchangeScreen
import com.example.kala.screens.AddExchangeScreen
import com.example.kala.screens.HelpScreen
import com.example.kala.screens.HomeScreen
import com.example.kala.screens.LanguageScreen
import com.example.kala.screens.OptionScreen
import com.example.kala.screens.RecordScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.homeScreen.route){
        composable(route = AppScreens.homeScreen.route){
            HomeScreen(navController)
        }
        composable(route = AppScreens.helpScreen.route){
            HelpScreen(navController)
        }
        composable(route = AppScreens.optionScreen.route){
            OptionScreen(navController)
        }
        composable(route = AppScreens.languageScreen.route){
            LanguageScreen(navController)
        }
        composable(route = AppScreens.addExchangeScreen.route){
            AddExchangeScreen(navController)
        }
        composable(route = AppScreens.recordScreen.route){
            RecordScreen(navController)
        }
        composable(
            route = AppScreens.aboutExchangeScreen.route + "/{month}" + "/{exchange}",
        ){
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val month = navBackStackEntry?.arguments?.getString("month")
            val exchange = navBackStackEntry?.arguments?.getInt("exchange")

            if (month != null && exchange != null) {
                AboutExchangeScreen(monthAssociated = month, exchange = exchange)
            } else {
                // TODO Handle the case where the parameters are null
            }
        }

    }
}