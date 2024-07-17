package com.example.kala.ui.components.inputs

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color
import com.example.kala.R
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType

/**
 * Colors and styles for text input fields using TextField in Compose.
 */
val inputTextColor = TextFieldColors(
    focusedTextColor = Color.Unspecified,
    unfocusedTextColor = Color.Unspecified,
    disabledTextColor = Color.Unspecified,
    errorTextColor = Color.Unspecified,
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    disabledContainerColor = Color.White,
    errorContainerColor = Color.White,
    cursorColor = Color.Unspecified,
    errorCursorColor = Color.Unspecified,
    textSelectionColors = TextSelectionColors(Color.White, Color.White),
    focusedIndicatorColor = Color.Unspecified,
    unfocusedIndicatorColor = Color.Unspecified,
    disabledIndicatorColor = Color.Unspecified,
    errorIndicatorColor = Color.Unspecified,
    focusedLeadingIconColor = Color.Unspecified,
    unfocusedLeadingIconColor = Color.Unspecified,
    disabledLeadingIconColor = Color.Unspecified,
    errorLeadingIconColor = Color.Unspecified,
    focusedTrailingIconColor = Color.Unspecified,
    unfocusedTrailingIconColor = Color.Unspecified,
    disabledTrailingIconColor = Color.Unspecified,
    errorTrailingIconColor = Color.Unspecified,
    focusedLabelColor = Color.Unspecified,
    unfocusedLabelColor = Color.Unspecified,
    disabledLabelColor = Color.Unspecified,
    errorLabelColor = Color.Unspecified,
    focusedPlaceholderColor = Color.Unspecified,
    unfocusedPlaceholderColor = Color.Unspecified,
    disabledPlaceholderColor = Color.Unspecified,
    errorPlaceholderColor = Color.Unspecified,
    focusedSupportingTextColor = Color.Unspecified,
    unfocusedSupportingTextColor = Color.Unspecified,
    disabledSupportingTextColor = Color.Unspecified,
    errorSupportingTextColor = Color.Unspecified,
    focusedPrefixColor = Color.Unspecified,
    unfocusedPrefixColor = Color.Unspecified,
    disabledPrefixColor = Color.Unspecified,
    errorPrefixColor = Color.Unspecified,
    focusedSuffixColor = Color.Unspecified,
    unfocusedSuffixColor = Color.Unspecified,
    disabledSuffixColor = Color.Unspecified,
    errorSuffixColor = Color.Unspecified
)

val USERNAME_LAYER_STI = R.string.username_layer_sti
val USERNAME_PLACEHOLDER_STI = R.string.username_placeholder_sti
val EMAIL_LAYER_STI = R.string.email_layer_sti
val EMAIL_PLACEHOLDER_STI = R.string.email_placeholder_sti
val PASSWORD_LAYER_STI = R.string.password_layer_sti
val PASSWORD_PLACEHOLDER_STI = R.string.password_placeholder_sti
val NEW_PASSWORD_LAYER_STI = R.string.new_password_layer_sti
val RESET_PASSWORD_LAYER_STI = R.string.reset_password_layer_sti

/**
 * Configuration enum for small text input fields, defining their layer, placeholder, and associated SVG file.
 * @param layer Resource ID for the layer text of the input field.
 * @param placeholder Resource ID for the placeholder text of the input field.
 * @param svgFile Resource ID for the SVG file associated with the input field.
 */
enum class SmallTextInputConfiguration(
    private val layer: Int,
    private val placeholder: Int,
    private val svgFile: Int,
) {
    USERNAME(
        USERNAME_LAYER_STI,
        USERNAME_PLACEHOLDER_STI,
        R.drawable.ic_account,
    ),
    EMAIL(
        EMAIL_LAYER_STI,
        EMAIL_PLACEHOLDER_STI,
        R.drawable.ic_email,
    ),
    PASSWORD(
        PASSWORD_LAYER_STI,
        PASSWORD_PLACEHOLDER_STI,
        R.drawable.ic_pass,
    ),
    NEW_PASSWORD(
        NEW_PASSWORD_LAYER_STI,
        PASSWORD_PLACEHOLDER_STI,
        R.drawable.ic_pass,
    ),
    REPEAT_PASSWORD(
        RESET_PASSWORD_LAYER_STI,
        PASSWORD_PLACEHOLDER_STI,
        R.drawable.ic_pass,
    );

    /**
     * Returns the resource ID for the layer text.
     */
    fun getLayer(): Int {
        return layer
    }

    /**
     * Returns the resource ID for the placeholder text.
     */
    fun getPlaceholder(): Int {
        return placeholder
    }

    /**
     * Returns the resource ID for the SVG file associated with the input configuration.
     */
    fun getSVGFile(): Int {
        return svgFile
    }

    /**
     * Checks if the input configuration represents a password field.
     */
    fun isPassword(): Boolean {
        return (this == PASSWORD) || (this == NEW_PASSWORD) || (this == REPEAT_PASSWORD)
    }

}

val SCOPE_LABEL_MI = R.string.scope_label_mi
val SCOPE_PLACEHOLDER_MI = R.string.scope_placeholder_mi
val TYPE_LABEL_MI = R.string.type_label_mi
val TYPE_PLACEHOLDER_MI = R.string.type_placeholder_mi

/**
 * Configuration enum for menu input fields, defining their label, placeholder, options, and associated SVG map.
 * @param label Resource ID for the label text of the menu input.
 * @param placeholder Resource ID for the placeholder text of the menu input.
 * @param options List of pairs representing options (name, label) available for selection.
 * @param svgMap Map associating each option (as String) with its corresponding SVG resource ID.
 */
enum class MenuInputConfiguration(
    private val label: Int,
    private val placeholder: Int,
    private val options: List<Pair<String, Int>>,
    private val svgMap: Map<String, Int>,
) {
    SCOPE(
        SCOPE_LABEL_MI,
        SCOPE_PLACEHOLDER_MI,
        listOf(
            Pair(MoneyExchangeScope.FOOD.toString(), MoneyExchangeScope.FOOD.getLabel()),
            Pair(MoneyExchangeScope.LEISURE.toString(), MoneyExchangeScope.LEISURE.getLabel()),
            Pair(MoneyExchangeScope.MEDICINE.toString(), MoneyExchangeScope.MEDICINE.getLabel()),
            Pair(MoneyExchangeScope.USEFUL.toString(), MoneyExchangeScope.USEFUL.getLabel()),
            Pair(MoneyExchangeScope.OTHER.toString(), MoneyExchangeScope.OTHER.getLabel()),
        ),
        mapOf(
            MoneyExchangeScope.FOOD.toString() to R.drawable.ic_food_scope,
            MoneyExchangeScope.LEISURE.toString() to R.drawable.ic_leisure_scope,
            MoneyExchangeScope.MEDICINE.toString() to R.drawable.ic_medicine_scope,
            MoneyExchangeScope.USEFUL.toString() to R.drawable.ic_utilities_scope,
            MoneyExchangeScope.OTHER.toString() to R.drawable.ic_other_scope,
        )
    ),
    TYPE(
        TYPE_LABEL_MI,
        TYPE_PLACEHOLDER_MI,
        listOf(
            Pair(MoneyExchangeType.INCOME.toString(), MoneyExchangeType.INCOME.getLabel()),
            Pair(MoneyExchangeType.EXPENSE.toString(), MoneyExchangeType.EXPENSE.getLabel()),
        ),
        mapOf(
            MoneyExchangeType.INCOME.toString() to R.drawable.ic_income,
            MoneyExchangeType.EXPENSE.toString() to R.drawable.ic_expense,
        )
    );

    /**
     * Configuration enum for menu input fields, defining their label, placeholder, options, and associated SVG map.
     */
    fun getLabel(): Int {
        return label
    }

    /**
     * Returns the list of option pairs (name, label) associated with the configuration.
     */
    fun getOptions(): List<Pair<String, Int>> {
        return options
    }

    /**
     * Returns the resource ID for the placeholder text.
     */

    fun getPlaceholder(): Int {
        return placeholder
    }

    /**
     * Returns the resource ID for the SVG icon associated with the specified option.
     * @param option The name of the option as String.
     * @return Resource ID of the SVG icon associated with the option, defaulting to R.drawable.ic_question if not found.
     */
    fun getSVG(option: String): Int {
        return svgMap.getOrDefault(option, R.drawable.ic_question)
    }
}

