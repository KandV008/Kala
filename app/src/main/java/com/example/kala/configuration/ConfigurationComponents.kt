package com.example.kala.configuration

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

/*
    SmallButtonComponent
 */

enum class SmallButtonConfiguration {
    ENGLISH, SPANISH;
}

val svgSmallButton: Map<SmallButtonConfiguration, Int> = mapOf(
    SmallButtonConfiguration.ENGLISH to R.drawable.ic_united_kingdom_flag,
    SmallButtonConfiguration.SPANISH to R.drawable.ic_spain_flag,
)

val actionsSmallButton: Map<SmallButtonConfiguration, () -> Unit> = mapOf(
    // TODO: Add functions associated with SmallButtonAction
)

/*
    MediumButtonComponent
 */

enum class MediumButtonConfiguration(private val displayName: String) {
    SEE_REPORT("See report"),
    SEE_RECORD("See record"),
    DELETE("Delete Card"),
    EDIT("Edit Card"),
    EXPENSE("Expense Details"),
    INCOME("Income Details");

    override fun toString(): String {
        return displayName
    }
}

val svgMediumButton: Map<MediumButtonConfiguration, Int> = mapOf(
    MediumButtonConfiguration.SEE_REPORT to R.drawable.ic_see_report,
    MediumButtonConfiguration.SEE_RECORD to R.drawable.ic_see_record,
    MediumButtonConfiguration.DELETE to R.drawable.ic_delete,
    MediumButtonConfiguration.EDIT to R.drawable.ic_edit,
    MediumButtonConfiguration.EXPENSE to R.drawable.ic_expense,
    MediumButtonConfiguration.INCOME to R.drawable.ic_income,
)

val actionsMediumButton: Map<MediumButtonConfiguration, () -> Unit> = mapOf(
    // TODO: Add functions associated with MediumButtonAction
)


/*
    LargeButtonComponent
 */

enum class LargeButtonConfiguration(private val displayName: String) {
    ADD_EXCHANGE("Add exchange"),
    CHANGE_NAME("Change name"),
    CHANGE_EMAIL("Change email"),
    SET_CURRENCY("Set currency"),
    SIGN_UP("Sign Up"),
    LOG_IN("Log In"),
    LOG_OUT("Log Out"),
    DELETE_USER("Delete User"),
    FORGOT_PASS("Forgot Pass?"),
    SEND_REQUEST("Send request");

    override fun toString(): String {
        return displayName
    }
}

val svgLargeButton: Map<LargeButtonConfiguration, Int> = mapOf(
    LargeButtonConfiguration.ADD_EXCHANGE to R.drawable.ic_exchange,
    LargeButtonConfiguration.CHANGE_NAME to R.drawable.ic_account,
    LargeButtonConfiguration.CHANGE_EMAIL to R.drawable.ic_email,
    LargeButtonConfiguration.SET_CURRENCY to R.drawable.ic_currency,
    LargeButtonConfiguration.SIGN_UP to R.drawable.ic_sign_up,
    LargeButtonConfiguration.LOG_IN to R.drawable.ic_log_in,
    LargeButtonConfiguration.LOG_OUT to R.drawable.ic_log_out,
    LargeButtonConfiguration.DELETE_USER to R.drawable.ic_delete_account,
    LargeButtonConfiguration.FORGOT_PASS to R.drawable.ic_question,
    LargeButtonConfiguration.SEND_REQUEST to R.drawable.ic_next,
)

val actionsLargeButton: Map<LargeButtonConfiguration, () -> Unit> = mapOf(
    // TODO: Add functions associated with LargeButtonAction
)

/*
    NavigationButtonComponent
 */

enum class NavigationButtonConfiguration {
    LANGUAGE, HELP, OPTIONS, BACK, HOME, NEXT;
}

val svgNavigationButton: Map<NavigationButtonConfiguration, Int> = mapOf(
    NavigationButtonConfiguration.LANGUAGE to R.drawable.ic_united_kingdom_flag, /* TODO Dynamic Icon */
    NavigationButtonConfiguration.HELP to R.drawable.ic_question,
    NavigationButtonConfiguration.OPTIONS to R.drawable.ic_options,
    NavigationButtonConfiguration.BACK to R.drawable.ic_back,
    NavigationButtonConfiguration.HOME to R.drawable.ic_home,
    NavigationButtonConfiguration.NEXT to R.drawable.ic_next,
)

val actionsNavigationButton: Map<NavigationButtonConfiguration, () -> Unit> = mapOf(
    /* TODO Functions associate to a configuration */
)

/*
    HeaderComponent
 */

enum class HeaderConfiguration {
    UNREGISTERED_USER, REGISTERED_USER, HELP_SCREEN, LANGUAGE_SCREEN;
}

val displayLeftHeader: Map<HeaderConfiguration, Pair<NavigationButtonConfiguration, Float>> = mapOf(
    HeaderConfiguration.UNREGISTERED_USER to Pair(NavigationButtonConfiguration.HELP, 1F),
    HeaderConfiguration.REGISTERED_USER to Pair(NavigationButtonConfiguration.HELP, 1F),
    HeaderConfiguration.HELP_SCREEN to Pair(NavigationButtonConfiguration.HELP, 0F),
    HeaderConfiguration.LANGUAGE_SCREEN to Pair(NavigationButtonConfiguration.HELP, 1F),
)

val displayCenterHeader: Map<HeaderConfiguration, Float> = mapOf(
    HeaderConfiguration.UNREGISTERED_USER to 0F,
    HeaderConfiguration.REGISTERED_USER to 1F,
    HeaderConfiguration.HELP_SCREEN to 1F,
    HeaderConfiguration.LANGUAGE_SCREEN to 1F,
)

val displayRightHeader: Map<HeaderConfiguration, Pair<NavigationButtonConfiguration, Float>> = mapOf(
    HeaderConfiguration.UNREGISTERED_USER to Pair(NavigationButtonConfiguration.LANGUAGE, 1F),
    HeaderConfiguration.REGISTERED_USER to Pair(NavigationButtonConfiguration.OPTIONS, 1F),
    HeaderConfiguration.HELP_SCREEN to Pair(NavigationButtonConfiguration.LANGUAGE, 1F),
    HeaderConfiguration.LANGUAGE_SCREEN to Pair(NavigationButtonConfiguration.OPTIONS, 0F),
)

/*
    FooterComponent
 */

enum class FooterConfiguration(){
    EMPTY, ONLY_BACK, ONLY_NEXT, BACK_AND_NEXT, BACK_AND_HOME, NEXT_AND_HOME, ALL
}

val displayLeftFooter: Map<FooterConfiguration, Float> = mapOf(
    FooterConfiguration.EMPTY to 0F,
    FooterConfiguration.ONLY_BACK to 1F,
    FooterConfiguration.ONLY_NEXT to 0F,
    FooterConfiguration.BACK_AND_NEXT to 1F,
    FooterConfiguration.BACK_AND_HOME to 1F,
    FooterConfiguration.NEXT_AND_HOME to 0F,
    FooterConfiguration.ALL to 1F,
)

val displayCenterFooter: Map<FooterConfiguration, Float> = mapOf(
    FooterConfiguration.EMPTY to 0F,
    FooterConfiguration.ONLY_BACK to 0F,
    FooterConfiguration.ONLY_NEXT to 0F,
    FooterConfiguration.BACK_AND_NEXT to 0F,
    FooterConfiguration.BACK_AND_HOME to 1F,
    FooterConfiguration.NEXT_AND_HOME to 1F,
    FooterConfiguration.ALL to 1F,
)

val displayRightFooter: Map<FooterConfiguration, Float> = mapOf(
    FooterConfiguration.EMPTY to 0F,
    FooterConfiguration.ONLY_BACK to 0F,
    FooterConfiguration.ONLY_NEXT to 1F,
    FooterConfiguration.BACK_AND_NEXT to 1F,
    FooterConfiguration.BACK_AND_HOME to 0F,
    FooterConfiguration.NEXT_AND_HOME to 1F,
    FooterConfiguration.ALL to 1F,
)