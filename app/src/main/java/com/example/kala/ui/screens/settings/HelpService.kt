package com.example.kala.ui.screens.settings

import com.example.kala.ui.screens.navigation.ABOUT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.ABOUT_MONTH_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.CHANGE_PASS_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.EDIT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.LANGUAGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.LOG_IN_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.MAIN_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.OPTION_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.RECORD_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.REPORT_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.SIGN_UP_SCREEN_ROUTE

private const val GET_TEXT_ADVICE_SCREEN_ACTION_MESSAGE =
    "[HelpService][ACTION] Get Text Advice Screen"
private const val GET_TEXT_ADVICE_SCREEN_RESULT_MESSAGE =
    "[HelpService][RESULT] Text for the Help Screen"

/**
 * Service class for managing the messages for the help screen.
 */
object HelpService {
    private var textAdviceScreen: Map<String, List<Int>> = mapOf(
        HOME_SCREEN_ROUTE to HOME_SCREEN_HELP,
        ADD_EXCHANGE_SCREEN_ROUTE to ADD_EXCHANGE_SCREEN_HELP,
        EDIT_EXCHANGE_SCREEN_ROUTE to EDIT_EXCHANGE_SCREEN_HELP,
        RECORD_SCREEN_ROUTE to RECORD_SCREEN_HELP,
        ABOUT_EXCHANGE_SCREEN_ROUTE to ABOUT_EXCHANGE_SCREEN_HELP,
        REPORT_SCREEN_ROUTE to REPORT_SCREEN_HELP,
        ABOUT_MONTH_SCREEN_ROUTE to ABOUT_MONTH_SCREEN_HELP,
        OPTION_SCREEN_ROUTE to OPTION_SCREEN_HELP,
        LANGUAGE_SCREEN_ROUTE to LANGUAGE_SCREEN_HELP,
        MAIN_SCREEN_ROUTE to MAIN_SCREEN_HELP,
        LOG_IN_SCREEN_ROUTE to LOG_IN_SCREEN_HELP,
        CHANGE_PASS_SCREEN_ROUTE to CHANGE_PASS_SCREEN_HELP,
        SIGN_UP_SCREEN_ROUTE to SIGN_UP_SCREEN_HELP,
    )

    /**
     * Retrieve the help text for a given screen.
     * @param screen The route of the screen for which help text is requested.
     * @return The list of help text associated with the specified screen, or an empty list if no help text is found.
     */
    fun getTextAdviceScreen(screen: String): List<Int> {
        println(GET_TEXT_ADVICE_SCREEN_ACTION_MESSAGE)
        val textForHelpScreen = textAdviceScreen[screen].orEmpty()
        println(GET_TEXT_ADVICE_SCREEN_RESULT_MESSAGE)
        return textForHelpScreen
    }

}