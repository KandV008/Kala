package com.example.kala.configuration

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
    General
 */

const val SVG_DESCRIPTION = "SVG from Button"
fun invalidArgument(){
    println("NOT VALID CONFIGURATION")
}

const val DEFAULT_FLOAT = 1F
const val NAME_APPLICATION = "Kala"
const val DEFAULT_STRING = "Sample Text"

/*
    HeaderComponent
 */

enum class HeaderConfiguration(
    private val left: Pair<NavigationButtonConfiguration, Float>,
    private val center: Float,
    private val right: Pair<NavigationButtonConfiguration, Float>
) {
    UNREGISTERED_USER (
        Pair(NavigationButtonConfiguration.HELP, 1F),
        0F,
        Pair(NavigationButtonConfiguration.LANGUAGE, 1F)
    ),
    REGISTERED_USER(
        Pair(NavigationButtonConfiguration.HELP, 1F),
        1F,
        Pair(NavigationButtonConfiguration.OPTIONS, 1F)
    ),
    HELP_SCREEN(
        Pair(NavigationButtonConfiguration.HELP, 0F),
        1F,
        Pair(NavigationButtonConfiguration.LANGUAGE, 1F)
    ),
    LANGUAGE_SCREEN(
        Pair(NavigationButtonConfiguration.HELP, 1F),
        1F,
        Pair(NavigationButtonConfiguration.OPTIONS, 0F)
    ),
    OPTION_SCREEN(
        Pair(NavigationButtonConfiguration.HELP, 1F),
        1F,
        Pair(NavigationButtonConfiguration.LANGUAGE, 1F)
    );

    fun left(): Pair<NavigationButtonConfiguration, Float>{
        return left
    }

    fun center(): Float{
        return center
    }

    fun right(): Pair<NavigationButtonConfiguration, Float>{
        return right
    }
}

/*
    FooterComponent
 */

enum class FooterConfiguration(
    private val left: Float,
    private val center: Float,
    private val right: Float,
){
    EMPTY(0F, 0F, 0F),
    ONLY_BACK(1F, 0F, 0F),
    ONLY_NEXT(0F, 0F, 1F),
    BACK_AND_NEXT(1F, 0F, 1F),
    BACK_AND_HOME(1F, 1F, 0F),
    NEXT_AND_HOME(0F, 1F, 1F),
    ALL(1F, 1F, 1F);

    fun left(): Float{
        return left
    }

    fun center(): Float{
        return center
    }

    fun right(): Float{
        return right
    }
}

/*
    LogoComponent
 */

enum class LogoConfiguration(private val size: Dp){
    SMALL(150.dp),
    LARGE(200.dp);

    fun getSize(): Dp {
        return size
    }
}

/*
    TitleComponent
 */

enum class TitleConfiguration(private val displayName: String){
    SIGN_UP("Sign Up"),
    LOG_IN("Log In"),
    RECOVERY_PASS("Recovery pass"),
    REQUEST_DONE("Request done"),
    CHANGE_PASS("Change pass"),
    CONFIRMATION("Confirmation"),
    OPTIONS("Options"),
    ADD_EXCHANGE("Add Exchange"),
    LANGUAGES("Languages"),
    RECORD("Record"),
    MORE_INFO("More Info"),
    HELP("Help"),
    REPORT("Report"),
    EXPENSE("Expense"),
    INCOME("Income");

    override fun toString(): String {
        return displayName
    }
}

/*
    ChartComponent
 */

enum class ChartConfiguration(
    private val alpha: Float,
){
    HOME_PAGE(0F),
    REPORT_PAGE(1F),
    ;

    fun alpha(): Float{
        return alpha
    }
}