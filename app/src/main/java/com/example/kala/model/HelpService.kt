package com.example.kala.model

import com.example.kala.configuration.ABOUT_EXCHANGE_SCREEN_HELP
import com.example.kala.configuration.ABOUT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.ABOUT_MONTH_SCREEN_HELP
import com.example.kala.configuration.ABOUT_MONTH_SCREEN_ROUTE
import com.example.kala.configuration.ADD_EXCHANGE_SCREEN_HELP
import com.example.kala.configuration.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.EDIT_EXCHANGE_SCREEN_HELP
import com.example.kala.configuration.EDIT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.HOME_SCREEN_HELP
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.LANGUAGE_SCREEN_HELP
import com.example.kala.configuration.LANGUAGE_SCREEN_ROUTE
import com.example.kala.configuration.OPTION_SCREEN_HELP
import com.example.kala.configuration.OPTION_SCREEN_ROUTE
import com.example.kala.configuration.RECORD_SCREEN_HELP
import com.example.kala.configuration.RECORD_SCREEN_ROUTE
import com.example.kala.configuration.REPORT_SCREEN_HELP
import com.example.kala.configuration.REPORT_SCREEN_ROUTE

private const val GET_TEXT_ADVICE_SCREEN_ACTION_MESSAGE = "[HelpService][ACTION] Get Text Advice Screen"
private const val GET_TEXT_ADVICE_SCREEN_RESULT_MESSAGE = "[HelpService][RESULT] Text for the Help Screen"

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
    )

    /**
     * Retrieve the help text for a given screen.
     * @param screen The route of the screen for which help text is requested.
     * @return The list of help text associated with the specified screen, or an empty list if no help text is found.
     */
    fun getTextAdviceScreen(screen: String): List<Int>{
        println(GET_TEXT_ADVICE_SCREEN_ACTION_MESSAGE)
        val textForHelpScreen = textAdviceScreen[screen].orEmpty()
        println(GET_TEXT_ADVICE_SCREEN_RESULT_MESSAGE)
        return textForHelpScreen
    }

}