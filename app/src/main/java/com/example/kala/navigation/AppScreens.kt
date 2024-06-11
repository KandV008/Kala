package com.example.kala.navigation

import com.example.kala.configuration.ABOUT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.ABOUT_MONTH_SCREEN_ROUTE
import com.example.kala.configuration.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.EDIT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.HELP_SCREEN_ROUTE
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.LANGUAGE_SCREEN_ROUTE
import com.example.kala.configuration.LOG_IN_SCREEN_ROUTE
import com.example.kala.configuration.MAIN_SCREEN_ROUTE
import com.example.kala.configuration.OPTION_SCREEN_ROUTE
import com.example.kala.configuration.RECORD_SCREEN_ROUTE
import com.example.kala.configuration.REPORT_SCREEN_ROUTE
import com.example.kala.configuration.SIGN_UP_SCREEN_ROUTE

/**
 * Sealed class representing different screens/routes in the app.
 *
 * @param route The route associated with the screen.
 */
sealed class AppScreens(val route: String) {
    object homeScreen: AppScreens(HOME_SCREEN_ROUTE)

    /**
     * Represents the option screen.
     */
    object optionScreen: AppScreens(OPTION_SCREEN_ROUTE)

    /**
     * Represents the help screen.
     */
    object helpScreen: AppScreens(HELP_SCREEN_ROUTE)

    /**
     * Represents the language selection screen.
     */
    object languageScreen: AppScreens(LANGUAGE_SCREEN_ROUTE)

    /**
     * Represents the screen for adding a new money exchange.
     */
    object addExchangeScreen: AppScreens(ADD_EXCHANGE_SCREEN_ROUTE)

    /**
     * Represents the record screen.
     */
    object recordScreen: AppScreens(RECORD_SCREEN_ROUTE)

    /**
     * Represents the screen showing details about a specific money exchange.
     */
    object aboutExchangeScreen: AppScreens(ABOUT_EXCHANGE_SCREEN_ROUTE)

    /**
     * Represents the screen for editing a existing money exchange.
     */
    object editExchangeScreen: AppScreens(EDIT_EXCHANGE_SCREEN_ROUTE)

    /**
     * Represents the report screen.
     */
    object reportScreen: AppScreens(REPORT_SCREEN_ROUTE)

    /**
     * Represent the screen showing details about a month
     */
    object aboutMonthScreen: AppScreens(ABOUT_MONTH_SCREEN_ROUTE)

    object mainScreen: AppScreens(MAIN_SCREEN_ROUTE)
    object signUpScreen: AppScreens(SIGN_UP_SCREEN_ROUTE)
    object logInScreen: AppScreens(LOG_IN_SCREEN_ROUTE)
}
