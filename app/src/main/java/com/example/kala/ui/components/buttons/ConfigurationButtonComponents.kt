package com.example.kala.ui.components.buttons

import android.content.Context
import com.example.kala.R
import com.example.kala.ui.screens.utilities.Utilities.getLocale

const val ENGLISH_ACTION = "en"
const val SPANISH_ACTION = "es"

/**
 * Enum representing configurations for small-sized buttons.
 *
 * @property svgFile The resource ID of the SVG icon associated with the button configuration.
 * @property action The action string associated with the button configuration.
 */
enum class SmallButtonConfiguration(
    private val svgFile: Int,
    private val action: String,
) {
    ENGLISH(
        R.drawable.ic_united_kingdom_flag,
        ENGLISH_ACTION
    ),
    SPANISH(
        R.drawable.ic_spain_flag,
        SPANISH_ACTION
    );

    /**
     * Returns the resource ID of the SVG icon for this button configuration.
     */
    fun getSVGFile(): Int {
        return svgFile
    }

    /**
     * Returns the action string associated with this button configuration.
     */
    fun getAction(): String {
        return action
    }
}

val SEE_REPORT_MD = R.string.see_report_md
val SEE_RECORD_MD = R.string.see_record_md
val SEE_DELETE_MD = R.string.see_delete_md
val EDIT_CARD_MD = R.string.edit_card_md
val EXPENSE_DETAILS_MD = R.string.expense_details_md
val INCOME_DETAILS_MD = R.string.income_details_md

/**
 * Enum representing configurations for medium-sized buttons.
 *
 * @property displayName The string resource ID representing the display name of the button.
 * @property svgFile The resource ID of the SVG icon associated with the button configuration.
 */
enum class MediumButtonConfiguration(
    private val displayName: Int,
    private val svgFile: Int,
) {
    SEE_REPORT(SEE_REPORT_MD, R.drawable.ic_see_report),
    SEE_RECORD(SEE_RECORD_MD, R.drawable.ic_see_record),
    DELETE(SEE_DELETE_MD, R.drawable.ic_delete),
    EDIT(EDIT_CARD_MD, R.drawable.ic_edit),
    EXPENSE(EXPENSE_DETAILS_MD, R.drawable.ic_expense),
    INCOME(INCOME_DETAILS_MD, R.drawable.ic_income);

    /**
     * Returns the string resource ID representing the display name of the button.
     */
    fun getDisplayName(): Int {
        return displayName
    }

    /**
     * Returns the resource ID of the SVG icon for this button configuration.
     */
    fun getSVGFile(): Int {
        return svgFile
    }
}

val ADD_EXCHANGE_LB = R.string.add_exchange_lb
val CHANGE_NAME_LB = R.string.change_name_lb
val CHANGE_EMAIL_LB = R.string.change_email_lb
val SET_CURRENCY_LB = R.string.set_currency_lb
val SIGN_UP_LB = R.string.sign_up_lb
val LOG_IN_LB = R.string.log_in_lb
val LOG_OUT_LB = R.string.log_out_lb
val DELETE_USER_LB = R.string.delete_user_lb
val FORGOT_PASS_LB = R.string.forgot_pass_lb
val SEND_REQUEST_LB = R.string.send_request_lb

/**
 * Enum representing configurations for large-sized buttons.
 *
 * @property displayName The string resource ID representing the display name of the button.
 * @property svgFile The resource ID of the SVG icon associated with the button configuration.
 */
enum class LargeButtonConfiguration(
    private val displayName: Int,
    private val svgFile: Int,
) {
    ADD_EXCHANGE(ADD_EXCHANGE_LB, R.drawable.ic_exchange),
    CHANGE_NAME(CHANGE_NAME_LB, R.drawable.ic_account),
    CHANGE_EMAIL(CHANGE_EMAIL_LB, R.drawable.ic_email),
    SET_CURRENCY(SET_CURRENCY_LB, R.drawable.ic_currency),
    SIGN_UP(SIGN_UP_LB, R.drawable.ic_sign_up),
    LOG_IN(LOG_IN_LB, R.drawable.ic_log_in),
    LOG_OUT(LOG_OUT_LB, R.drawable.ic_log_out),
    DELETE_USER(DELETE_USER_LB, R.drawable.ic_delete_account),
    FORGOT_PASS(FORGOT_PASS_LB, R.drawable.ic_question),
    SEND_REQUEST(SEND_REQUEST_LB, R.drawable.ic_next);

    /**
     * Returns the string resource ID representing the display name of the button.
     */
    fun getDisplayName(): Int {
        return displayName
    }

    /**
     * Returns the resource ID of the SVG icon for this button configuration.
     */
    fun getSVGFile(): Int {
        return svgFile
    }
}

fun getLanguageIconViaContext(context: Context): Int {
    val locale = getLocale(context)
    return getLanguageIcon(locale)
}

private fun getLanguageIcon(locale: String) = when (locale) {
    SPANISH_ACTION -> R.drawable.ic_spain_flag
    else -> R.drawable.ic_united_kingdom_flag
}

/**
 * Enum representing configurations for navigation buttons.
 *
 * @property svgFile The resource ID of the SVG icon associated with the button configuration.
 */
enum class NavigationButtonConfiguration(
    private var svgFile: Int,
) {
    LANGUAGE(
        R.drawable.ic_united_kingdom_flag
    ),
    HELP(
        R.drawable.ic_question,
    ),
    OPTIONS(
        R.drawable.ic_options,
    ),
    BACK(
        R.drawable.ic_back,
    ),
    HOME(
        R.drawable.ic_home,
    ),
    NEXT(
        R.drawable.ic_next,
    );

    /**
     * Returns the resource ID of the SVG icon for this button configuration.
     */
    fun getSVGFile(): Int {
        return svgFile
    }

    /**
     * Updates the SVG icon for this button configuration.
     *
     * @param newIcon The new resource ID of the SVG icon.
     */
    fun updateIcon(newIcon: Int) {
        svgFile = newIcon
    }

    /**
     * Updates the SVG icon for this button configuration based on language.
     *
     * @param language The language action string (`ENGLISH_ACTION` or `SPANISH_ACTION`).
     */
    fun updateIcon(language: String) {
        svgFile = getLanguageIcon(language)
    }
}

/**
 * Enum representing configurations for chart navigation buttons.
 *
 * @property svgFile The resource ID of the SVG icon associated with the button configuration.
 */
enum class ChartButtonConfiguration(
    private val svgFile: Int,
) {
    LEFT(R.drawable.ic_previous),
    RIGHT(R.drawable.ic_succesor),
    ;

    /**
     * Returns the resource ID of the SVG icon for this button configuration.
     */
    fun getSVGFile(): Int {
        return svgFile
    }
}