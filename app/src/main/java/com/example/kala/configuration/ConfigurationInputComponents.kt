package com.example.kala.configuration

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kala.R
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType

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

    @RequiresApi(Build.VERSION_CODES.N)
    fun getSVG(option: String): Int{
        return svgMap.getOrDefault(option, R.drawable.ic_question)
    }
}