package com.example.kala.ui.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kala.R
import com.example.kala.ui.components.buttons.NavigationButtonConfiguration

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