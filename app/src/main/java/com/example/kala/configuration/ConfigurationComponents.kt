package com.example.kala.configuration

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kala.R

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

val SIGN_UP_TITLE = R.string.sign_up_title
val LOG_IN_TITLE = R.string.log_in_title
val RECOVERY_PASS_TITLE = R.string.recovery_pass_title
val REQUEST_DONE_TITLE = R.string.request_done_title
val CHANGE_PASS_TITLE = R.string.change_pass_title
val CONFIRMATION_TITLE = R.string.confirmation_title
val OPTIONS_TITLE = R.string.options
val ADD_EXCHANGE_TITLE = R.string.add_exchange_title
val LANGUAGES_TITLE = R.string.languages_title
val RECORD_TITLE = R.string.record_title
val MORE_INFO_TITLE = R.string.more_info_title
val HELP_TITLE = R.string.help_title
val REPORT_TITLE = R.string.report_title
val EXPENSE_TITLE = R.string.expense_title
val INCOME_TITLE = R.string.income_title

enum class TitleConfiguration(private val displayName: Int){
    SIGN_UP(SIGN_UP_TITLE),
    LOG_IN(LOG_IN_TITLE),
    RECOVERY_PASS(RECOVERY_PASS_TITLE),
    REQUEST_DONE(REQUEST_DONE_TITLE),
    CHANGE_PASS(CHANGE_PASS_TITLE),
    CONFIRMATION(CONFIRMATION_TITLE),
    OPTIONS(OPTIONS_TITLE),
    ADD_EXCHANGE(ADD_EXCHANGE_TITLE),
    LANGUAGES(LANGUAGES_TITLE),
    RECORD(RECORD_TITLE),
    MORE_INFO(MORE_INFO_TITLE),
    HELP(HELP_TITLE),
    REPORT(REPORT_TITLE),
    EXPENSE(EXPENSE_TITLE),
    INCOME(INCOME_TITLE);

    fun getDisplayName(): Int {
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