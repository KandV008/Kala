package com.example.kala.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kala.ui.screens.navigation.AppScreens
import com.example.kala.ui.screens.moneyExchange.AboutExchangeScreen
import com.example.kala.ui.screens.monthInformation.AboutMonthScreen
import com.example.kala.ui.screens.moneyExchange.AddExchangeScreen
import com.example.kala.ui.screens.moneyExchange.EditExchangeScreen
import com.example.kala.ui.screens.settings.HelpScreen
import com.example.kala.ui.screens.settings.LanguageScreen
import com.example.kala.ui.screens.settings.OptionScreen
import com.example.kala.ui.screens.moneyExchange.RecordScreen
import com.example.kala.ui.screens.monthInformation.ReportScreen
import com.example.kala.ui.screens.authentication.LogInScreen
import com.example.kala.ui.screens.authentication.ChangePasswordScreen
import com.example.kala.ui.screens.authentication.SignUpScreen

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
private const val MAIN_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Main Screen"
private const val SIGN_UP_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Sign Up Screen"
private const val LOG_IN_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Log In Screen"
private const val RECOVERY_PASS_SCREEN_NAVIGATION_MESSAGE = "[AppNavigation][ACTION] Navigate to Log In Screen"

/**
 * Composable function defining the app's navigation.
 */
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.mainScreen.route){
        composable(route = AppScreens.homeScreen.route){
            println(HOME_SCREEN_NAVIGATION_MESSAGE)
            HomeScreen(navController)
        }
        composable(route = AppScreens.helpScreen.route + "/{triggerScreen}"){
            println(HELP_SCREEN_NAVIGATION_MESSAGE)
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val triggerScreen = navBackStackEntry?.arguments?.getString("triggerScreen")

            if (triggerScreen != null){
                HelpScreen(navController, triggerScreen)

            }
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
            val exchange = navBackStackEntry?.arguments?.getString("exchange")

            if (month != null && exchange != null) {
                AboutExchangeScreen(navController, month, exchange.toInt())
            }
        }
        composable(
            route = AppScreens.editExchangeScreen.route + "/{month}" + "/{exchange}",
        ){
            println(EDIT_EXCHANGE_SCREEN_NAVIGATION_MESSAGE)
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val month = navBackStackEntry?.arguments?.getString("month")
            val exchange = navBackStackEntry?.arguments?.getString("exchange")

            if (month != null && exchange != null) {
                EditExchangeScreen(navController, month, exchange.toInt())
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
        composable(route = AppScreens.mainScreen.route){
            println(MAIN_SCREEN_NAVIGATION_MESSAGE)
            MainScreen(navController)
        }
        composable(route = AppScreens.signUpScreen.route){
            println(SIGN_UP_SCREEN_NAVIGATION_MESSAGE)
            SignUpScreen(navController)
        }
        composable(route = AppScreens.logInScreen.route){
            println(LOG_IN_SCREEN_NAVIGATION_MESSAGE)
            LogInScreen(navController)
        }
        composable(route = AppScreens.recoveryPassScreen.route){
            println(RECOVERY_PASS_SCREEN_NAVIGATION_MESSAGE)
            ChangePasswordScreen(navController)
        }
    }
}
