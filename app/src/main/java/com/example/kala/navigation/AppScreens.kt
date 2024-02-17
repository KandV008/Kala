package com.example.kala.navigation

import com.example.kala.configuration.HELP_SCREEN_ROUTE
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.LANGUAGE_SCREEN_ROUTE
import com.example.kala.configuration.OPTION_SCREEN_ROUTE

sealed class AppScreens(val route: String) {
    object homeScreen: AppScreens(HOME_SCREEN_ROUTE)
    object optionScreen: AppScreens(OPTION_SCREEN_ROUTE)
    object helpScreen: AppScreens(HELP_SCREEN_ROUTE)

    object languageScreen: AppScreens(LANGUAGE_SCREEN_ROUTE)

}