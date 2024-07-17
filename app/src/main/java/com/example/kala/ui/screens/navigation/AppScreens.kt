package com.example.kala.ui.screens.navigation

/**
 * Sealed class representing different screens/routes in the app.
 *
 * @param route The route associated with the screen.
 */
sealed class AppScreens(val route: String) {
    /**
     * Represents the home screen.
     */
    object homeScreen : AppScreens(HOME_SCREEN_ROUTE)

    /**
     * Represents the option screen.
     */
    object optionScreen : AppScreens(OPTION_SCREEN_ROUTE)

    /**
     * Represents the help screen.
     */
    object helpScreen : AppScreens(HELP_SCREEN_ROUTE)

    /**
     * Represents the language selection screen.
     */
    object languageScreen : AppScreens(LANGUAGE_SCREEN_ROUTE)

    /**
     * Represents the screen for adding a new money exchange.
     */
    object addExchangeScreen : AppScreens(ADD_EXCHANGE_SCREEN_ROUTE)

    /**
     * Represents the record screen.
     */
    object recordScreen : AppScreens(RECORD_SCREEN_ROUTE)

    /**
     * Represents the screen showing details about a specific money exchange.
     */
    object aboutExchangeScreen : AppScreens(ABOUT_EXCHANGE_SCREEN_ROUTE)

    /**
     * Represents the screen for editing a existing money exchange.
     */
    object editExchangeScreen : AppScreens(EDIT_EXCHANGE_SCREEN_ROUTE)

    /**
     * Represents the report screen.
     */
    object reportScreen : AppScreens(REPORT_SCREEN_ROUTE)

    /**
     * Represent the screen showing details about a month
     */
    object aboutMonthScreen : AppScreens(ABOUT_MONTH_SCREEN_ROUTE)

    /**
     * Represents the main screen.
     */
    object mainScreen : AppScreens(MAIN_SCREEN_ROUTE)

    /**
     * Represents the sign up screen.
     */
    object signUpScreen : AppScreens(SIGN_UP_SCREEN_ROUTE)

    /**
     * Represents the log in screen.
     */
    object logInScreen : AppScreens(LOG_IN_SCREEN_ROUTE)

    /**
     * Represents the password recovery screen.
     */
    object recoveryPassScreen : AppScreens(CHANGE_PASS_SCREEN_ROUTE)
}
