package com.example.kala.configuration

import com.example.kala.R

/*
    SmallButtonComponent
 */

val ENGLISH_ACTION = "en";
val SPANISH_ACTION = "es";

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

    fun getSVGFile(): Int{
        return svgFile
    }

    fun  getAction(): String{
        return action
    }
}

/*
    MediumButtonComponent
 */

val SEE_REPORT_MD = R.string.see_report_md
val SEE_RECORD_MD = R.string.see_record_md
val SEE_DELETE_MD = R.string.see_delete_md
val EDIT_CARD_MD = R.string.edit_card_md
val EXPENSE_DETAILS_MD = R.string.expense_details_md
val INCOME_DETAILS_MD = R.string.income_details_md


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

    fun getDisplayName(): Int {
        return displayName
    }

    fun getSVGFile(): Int{
        return svgFile
    }
}

/*
    LargeButtonComponent
 */

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

enum class LargeButtonConfiguration(
    private val displayName: Int,
    private val svgFile: Int,
    private val route: String,
) {
    ADD_EXCHANGE(ADD_EXCHANGE_LB, R.drawable.ic_exchange, ADD_EXCHANGE_SCREEN_ROUTE),
    CHANGE_NAME(CHANGE_NAME_LB, R.drawable.ic_account, "" /* TODO */),
    CHANGE_EMAIL(CHANGE_EMAIL_LB, R.drawable.ic_email,  "" /* TODO */),
    SET_CURRENCY(SET_CURRENCY_LB, R.drawable.ic_currency,  "" /* TODO */),
    SIGN_UP(SIGN_UP_LB, R.drawable.ic_sign_up, "" /* TODO */),
    LOG_IN(LOG_IN_LB, R.drawable.ic_log_in,  "" /* TODO */),
    LOG_OUT(LOG_OUT_LB, R.drawable.ic_log_out,  "" /* TODO */),
    DELETE_USER(DELETE_USER_LB, R.drawable.ic_delete_account,  "" /* TODO */),
    FORGOT_PASS(FORGOT_PASS_LB, R.drawable.ic_question,  "" /* TODO */),
    SEND_REQUEST(SEND_REQUEST_LB, R.drawable.ic_next,  "" /* TODO */);

    fun getDisplayName(): Int {
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