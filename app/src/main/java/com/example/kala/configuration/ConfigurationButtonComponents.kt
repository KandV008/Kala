package com.example.kala.configuration

import com.example.kala.R

/*
    SmallButtonComponent
 */

enum class SmallButtonConfiguration(
    private val svgFile: Int,
    //private val function: () -> Unit, TODO Functions associate to a configuration
) {
    ENGLISH(R.drawable.ic_united_kingdom_flag),
    SPANISH(R.drawable.ic_spain_flag);

    fun getSVGFile(): Int{
        return svgFile
    }
}

/*
    MediumButtonComponent
 */

enum class MediumButtonConfiguration(
    private val displayName: String,
    private val svgFile: Int,
    //private val function: () -> Unit, TODO Functions associate to a configuration
) {
    SEE_REPORT("See report", R.drawable.ic_see_report),
    SEE_RECORD("See record", R.drawable.ic_see_record),
    DELETE("Delete Card", R.drawable.ic_delete),
    EDIT("Edit\nCard", R.drawable.ic_edit),
    EXPENSE("Expense Details", R.drawable.ic_expense),
    INCOME("Income Details", R.drawable.ic_income);

    override fun toString(): String {
        return displayName
    }

    fun getSVGFile(): Int{
        return svgFile
    }
}

/*
    LargeButtonComponent
 */

enum class LargeButtonConfiguration(
    private val displayName: String,
    private val svgFile: Int,
    private val route: String,
) {
    ADD_EXCHANGE("Add exchange", R.drawable.ic_exchange, ADD_EXCHANGE_SCREEN_ROUTE),
    CHANGE_NAME("Change name", R.drawable.ic_account, "" /* TODO */),
    CHANGE_EMAIL("Change email", R.drawable.ic_email,  "" /* TODO */),
    SET_CURRENCY("Set currency", R.drawable.ic_currency,  "" /* TODO */),
    SIGN_UP("Sign Up", R.drawable.ic_sign_up, "" /* TODO */),
    LOG_IN("Log In", R.drawable.ic_log_in,  "" /* TODO */),
    LOG_OUT("Log Out", R.drawable.ic_log_out,  "" /* TODO */),
    DELETE_USER("Delete User", R.drawable.ic_delete_account,  "" /* TODO */),
    FORGOT_PASS("Forgot Pass?", R.drawable.ic_question,  "" /* TODO */),
    SEND_REQUEST("Send request", R.drawable.ic_next,  "" /* TODO */);

    override fun toString(): String {
        return displayName
    }

    fun getSVGFile(): Int{
        return svgFile
    }

    fun getRoute(): String{
        return route
    }
}

/*
    NavigationButtonComponent
 */

enum class NavigationButtonConfiguration(
    private val svgFile: Int,
) {
    LANGUAGE(
        R.drawable.ic_united_kingdom_flag,
    ), /* TODO Dynamic Icon */
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

    fun getSVGFile(): Int{
        return svgFile
    }
}

/*
    ChartButtonComponent
 */

enum class ChartButtonConfiguration(
    private val svgFile: Int,
){
    LEFT(R.drawable.ic_previous),
    RIGHT(R.drawable.ic_succesor),
    ;

    fun getSVGFile(): Int{
        return svgFile
    }
}