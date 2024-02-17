package com.example.kala.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kala.screens.HelpScreen
import com.example.kala.screens.HomeScreen
import com.example.kala.screens.LanguageScreen
import com.example.kala.screens.OptionScreen

@RequiresApi(Build.VERSION_CODES.N)
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

    }
}