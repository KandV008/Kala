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

const val DEFAULT_INT = -1
const val DEFAULT_FLOAT = 1F
const val NAME_APPLICATION = "Kala"

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
    DELETE("Delete Card",R.drawable.ic_delete),
    EDIT("Edit Card",R.drawable.ic_edit),
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
    //private val function: () -> Unit, TODO Functions associate to a configuration
) {
    ADD_EXCHANGE("Add exchange", R.drawable.ic_exchange),
    CHANGE_NAME("Change name", R.drawable.ic_account),
    CHANGE_EMAIL("Change email",R.drawable.ic_email),
    SET_CURRENCY("Set currency",R.drawable.ic_currency),
    SIGN_UP("Sign Up", R.drawable.ic_sign_up),
    LOG_IN("Log In", R.drawable.ic_log_in),
    LOG_OUT("Log Out", R.drawable.ic_log_out),
    DELETE_USER("Delete User", R.drawable.ic_delete_account),
    FORGOT_PASS("Forgot Pass?", R.drawable.ic_question),
    SEND_REQUEST("Send request", R.drawable.ic_next);

    override fun toString(): String {
        return displayName
    }

    fun getSVGFile(): Int{
        return svgFile
    }
}

/*
    NavigationButtonComponent
 */

enum class NavigationButtonConfiguration(
    private val svgFile: Int,
    //private val function: () -> Unit, TODO Functions associate to a configuration
) {
    LANGUAGE(R.drawable.ic_united_kingdom_flag), /* TODO Dynamic Icon */
    HELP(R.drawable.ic_question),
    OPTIONS(R.drawable.ic_options),
    BACK(R.drawable.ic_back),
    HOME(R.drawable.ic_back),
    NEXT(R.drawable.ic_next);

    fun getSVGFile(): Int{
        return svgFile
    }
}

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
        return size;
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