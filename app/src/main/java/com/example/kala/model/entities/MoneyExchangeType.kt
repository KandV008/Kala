package com.example.kala.model.entities

import com.example.kala.R

val EXPENSE_TYPE_LABEL = R.string.expense_type_label
val INCOME_TYPE_LABEL = R.string.income_type_label

/**
 * Enum representing the type of a money exchange.
 */
enum class MoneyExchangeType(private val label: Int) {
    EXPENSE(EXPENSE_TYPE_LABEL),
    INCOME(INCOME_TYPE_LABEL);

    fun getLabel(): Int {
        return label
    }
}
