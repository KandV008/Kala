package com.example.kala.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kala.screens.AboutExchangeScreen
import com.example.kala.screens.AboutMonthScreen
import com.example.kala.screens.AddExchangeScreen
import com.example.kala.screens.EditExchangeScreen
import com.example.kala.screens.HelpScreen
import com.example.kala.screens.HomeScreen
import com.example.kala.screens.LanguageScreen
import com.example.kala.screens.OptionScreen
import com.example.kala.screens.RecordScreen
import com.example.kala.screens.ReportScreen

private const val HOME_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Home Screen"
private const val HELP_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Help Screen"
private const val OPTION_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Option Screen"
private const val LANGUAGE_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Language Screen"
private const val ADD_EXCHANGE_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Add Exchange Screen"
private const val RECORD_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Record Screen"
private const val ABOUT_EXCHANGE_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to About Exchange Screen"
private const val EDIT_EXCHANGE_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Edit Exchange Screen"
private const val REPORT_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Report Screen"
private const val ABOUT_MONTH_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to About Month Screen"
private const val MONTH_ARGUMENT_ADVICE_MESSAGE = "[AppNavigation][ADVICE] Month: "
private const val EXCHANGE_ARGUMENT_ADVICE_MESSAGE = "[AppNavigation][ADVICE] Exchange: "
private const val TYPE_ARGUMENT_ADVICE_MESSAGE = "[AppNavigation][ADVICE] Type: "
private const val MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE = "[AppNavigation][ERROR] Month Id or/and Exchange Id are null"
private const val MONTH_ID_ERROR_MESSAGE = "[AppNavigation][ERROR] Month Id is null"
private const val MONTH_ID_OR_TYPE_ERROR_MESSAGE = "[AppNavigation][ERROR] Month Id or/and Type are null"

/**
 * Composable function defining the app's navigation.
 */
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.homeScreen.route){
        composable(route = AppScreens.homeScreen.route){
            println(HOME_SCREEN_NAVIGATION_MESSAGE)
            HomeScreen(navController)
        }
        composable(route = AppScreens.helpScreen.route){
            println(HELP_SCREEN_NAVIGATION_MESSAGE)
            HelpScreen(navController)
        }
        composable(route = AppScreens.optionScreen.route){
            println(OPTION_SCREEN_NAVIGATION_MESSAGE)
            OptionScreen(navController)
        }
        composable(route = AppScreens.languageScreen.route){
            println(LANGUAGE_SCREEN_NAVIGATION_MESSAGE)
            LanguageScreen(navController)
        }
        composable(route = AppScreens.addExchangeScreen.route){
            println(ADD_EXCHANGE_SCREEN_NAVIGATION_MESSAGE)
            AddExchangeScreen(navController)
        }
        composable(route = AppScreens.recordScreen.route){
            println(RECORD_SCREEN_NAVIGATION_MESSAGE)
            RecordScreen(navController)
        }
        composable(
            route = AppScreens.aboutExchangeScreen.route + "/{month}" + "/{exchange}",
        ){
            println(ABOUT_EXCHANGE_SCREEN_NAVIGATION_MESSAGE)
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val month = navBackStackEntry?.arguments?.getString("month")
            val exchange = navBackStackEntry?.arguments?.getInt("exchange")

            if (month != null && exchange != null) {
                AboutExchangeScreen(navController, month, exchange)
            }
        }
        composable(
            route = AppScreens.editExchangeScreen.route + "/{month}" + "/{exchange}",
        ){
            println(EDIT_EXCHANGE_SCREEN_NAVIGATION_MESSAGE)
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val month = navBackStackEntry?.arguments?.getString("month")
            val exchange = navBackStackEntry?.arguments?.getInt("exchange")

            if (month != null && exchange != null) {
                EditExchangeScreen(navController, month, exchange)
            }
        }
        composable(route = AppScreens.reportScreen.route + "/{month}"){
            println(REPORT_SCREEN_NAVIGATION_MESSAGE)
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val month = navBackStackEntry?.arguments?.getString("month")

            if (month != null) {
                ReportScreen(navController, month)
            }
        }
        composable(route = AppScreens.aboutMonthScreen.route + "/{month}" + "/{type}"){
            println(ABOUT_MONTH_SCREEN_NAVIGATION_MESSAGE)
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val month = navBackStackEntry?.arguments?.getString("month")
            val type = navBackStackEntry?.arguments?.getString("type")

            if (month != null && type != null) {
                AboutMonthScreen(navController, month, type)
            }
        }
    }
}
