package com.example.kala.ui.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kala.R

const val SVG_DESCRIPTION = "SVG from Button"
const val DEFAULT_FLOAT = 1F
const val NAME_APPLICATION = "Kala"

/**
 * Enum defining different configurations for the logo component.
 *
 * @property size The size of the logo in Dp (density-independent pixels).
 */
enum class LogoConfiguration(private val size: Dp) {
    SMALL(150.dp),
    LARGE(200.dp);

    /**
     * Retrieves the size of the logo.
     *
     * @return The size of the logo in Dp.
     */
    fun getSize(): Dp {
        return size
    }
}

val SIGN_UP_TITLE = R.string.sign_up_title
val LOG_IN_TITLE = R.string.log_in_title
val RECOVERY_PASS_TITLE = R.string.recovery_pass_title
val CHANGE_PASS_TITLE = R.string.change_pass_title
val OPTIONS_TITLE = R.string.options
val ADD_EXCHANGE_TITLE = R.string.add_exchange_title
val LANGUAGES_TITLE = R.string.languages_title
val RECORD_TITLE = R.string.record_title
val MORE_INFO_TITLE = R.string.more_info_title
val HELP_TITLE = R.string.help_title
val REPORT_TITLE = R.string.report_title
val EXPENSE_TITLE = R.string.expense_title
val INCOME_TITLE = R.string.income_title

/**
 * Enum class representing various screen titles used in the application.
 *
 * Each enum constant is associated with a title resource ID from `R.string`.
 * This enum provides a centralized way to access title resources throughout the application.
 *
 * @property displayName The title resource ID associated with each enum constant.
 */
enum class TitleConfiguration(private val displayName: Int) {
    SIGN_UP(SIGN_UP_TITLE),
    LOG_IN(LOG_IN_TITLE),
    RECOVERY_PASS(RECOVERY_PASS_TITLE),
    CHANGE_PASS(CHANGE_PASS_TITLE),
    OPTIONS(OPTIONS_TITLE),
    ADD_EXCHANGE(ADD_EXCHANGE_TITLE),
    LANGUAGES(LANGUAGES_TITLE),
    RECORD(RECORD_TITLE),
    MORE_INFO(MORE_INFO_TITLE),
    HELP(HELP_TITLE),
    REPORT(REPORT_TITLE),
    EXPENSE(EXPENSE_TITLE),
    INCOME(INCOME_TITLE);

    /**
     * Retrieves the title resource ID associated with the enum constant.
     *
     * @return The title resource ID as an integer.
     */
    fun getDisplayName(): Int {
        return displayName
    }
}

/**
 * Enum defining configurations for charts in the application.
 *
 * @property alpha The alpha value for the chart configuration.
 */
enum class ChartConfiguration(
    private val alpha: Float,
) {
    HOME_PAGE(0F),
    REPORT_PAGE(1F),
    ;

    /**
     * Retrieves the alpha value of the chart configuration.
     *
     * @return The alpha value of the chart configuration.
     */
    fun alpha(): Float {
        return alpha
    }
}