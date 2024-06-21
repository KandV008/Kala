package com.example.kala.ui.screens.utilities

import com.example.kala.R
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType


val errorMessageList: MutableList<Int> = mutableListOf()

private val INVALID_VALUE_ERROR_MESSAGE = R.string.invalid_value_error_message
private val INVALID_TYPE_ERROR_MESSAGE = R.string.invalid_type_error_message
private val INVALID_SCOPE_ERROR_MESSAGE = R.string.invalid_scope_error_message
private val INVALID_EMAIL_ERROR_MESSAGE = R.string.invalid_email_error_message
private val INVALID_PASSWORD_ERROR_MESSAGE = R.string.invalid_password_error_message

object FormValidation {

    /**
     * Checks if the provided values for exchange are valid.
     *
     * @return `true` if all values are valid, `false` otherwise.
     */
    fun isValidSignUp(
        email: String,
        password: String
    ): Boolean {
        if (isNotValidEmail(email)){
            errorMessageList.add(INVALID_EMAIL_ERROR_MESSAGE)
        }

        if (isNotValidPassword(password)){
            errorMessageList.add(INVALID_PASSWORD_ERROR_MESSAGE)
        }

        return errorMessageList.isEmpty()
    }

    private fun isNotValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
        return !emailRegex.toRegex().matches(email)
    }

    private fun isNotValidPassword(password: String): Boolean{
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"
        return !passwordRegex.toRegex().matches(password)
    }

    /**
     * Checks if the provided values for exchange are valid.
     *
     * @param valueExchange The value of the exchange as a string.
     * @param typeExchange The type of exchange as a string.
     * @param scopeExchange The scope of exchange as a string.
     * @return `true` if all values are valid, `false` otherwise.
     */
    fun isValidForm(
        valueExchange: String,
        typeExchange: String,
        scopeExchange: String,
    ): Boolean {
        try {
            val toDouble = valueExchange.toDouble()
            if (toDouble <= 0) {
                errorMessageList.add(INVALID_VALUE_ERROR_MESSAGE)
            }
        } catch (e: NumberFormatException){
            errorMessageList.add(INVALID_VALUE_ERROR_MESSAGE)
        }

        try {
            MoneyExchangeType.valueOf(typeExchange)
        } catch (e: IllegalArgumentException) {
            errorMessageList.add(INVALID_TYPE_ERROR_MESSAGE)
        }

        try {
            MoneyExchangeScope.valueOf(scopeExchange)
        } catch (e: IllegalArgumentException) {
            errorMessageList.add(INVALID_SCOPE_ERROR_MESSAGE)
        }

        return errorMessageList.isEmpty()
    }
}