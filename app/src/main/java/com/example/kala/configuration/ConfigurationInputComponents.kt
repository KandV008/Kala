package com.example.kala.configuration

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color
import com.example.kala.R
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType

/*
    General
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

/*
    SmallTextInputComponent
 */

enum class SmallTextInputConfiguration(
    private val layer: String,
    private val placeholder: String,
    private val svgFile: Int,
){
    USERNAME(
        "Username",
        "exampleUser",
        R.drawable.ic_account,
    ),
    EMAIL(
        "Email",
        "youremail@email.com",
        R.drawable.ic_email,
    ),
    PASSWORD(
        "Password",
        "******",
        R.drawable.ic_password,
    ),
    NEW_PASSWORD(
        "New password",
        "******",
        R.drawable.ic_password,
    ),
    REPEAT_PASSWORD(
        "Repeat password",
        "******",
        R.drawable.ic_password,
    );

    fun getLayer(): String{
        return layer
    }

    fun getPlaceholder(): String{
        return placeholder
    }

    fun getSVGFile(): Int{
        return svgFile
    }

    fun isPassword(): Boolean{
        return (this == PASSWORD) || (this == NEW_PASSWORD) || (this == REPEAT_PASSWORD)
    }

}

/*
    MenuInputComponent
 */

enum class MenuInputConfiguration(
    private val label: String,
    private val placeholder: String,
    private val options: List<String>,
    private val svgMap: Map<String, Int>,
){
    SCOPE(
        "Scope of the exchange",
        "Select Scope",
        listOf(
            MoneyExchangeScope.FOOD.toString(),
            MoneyExchangeScope.LEISURE.toString(),
            MoneyExchangeScope.MEDICINE.toString(),
            MoneyExchangeScope.USEFUL.toString(),
            MoneyExchangeScope.OTHER.toString(),
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
        "Type of the exchange",
        "Select Type",
        listOf(
            MoneyExchangeType.INCOME.toString(),
            MoneyExchangeType.EXPENSE.toString(),
        ),
        mapOf(
            MoneyExchangeType.INCOME.toString() to R.drawable.ic_income,
            MoneyExchangeType.EXPENSE.toString() to R.drawable.ic_expense,
            )
    );

    fun getLabel(): String{
        return label
    }

    fun getOptions(): List<String>{
        return options
    }

    fun getPlaceholder(): String{
        return placeholder
    }

    fun getSVG(option: String): Int{
        return svgMap.getOrDefault(option, R.drawable.ic_question)
    }
}

