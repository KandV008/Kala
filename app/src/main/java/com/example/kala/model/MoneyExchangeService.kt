package com.example.kala.model

import com.example.kala.model.entities.MoneyExchange
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.model.entities.MonthInformation
import com.example.kala.model.storage.MoneyExchangeStorage

private const val ADD_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Add Money Exchange"
private const val ADD_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Added new Money Exchange"
private const val GET_ALL_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Get All Money Exchange"
private const val GET_ALL_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] All Money Exchanges"
private const val GET_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Get Money Exchange"
private const val MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Specific Money Exchange"
private const val DELETE_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Delete Money Exchange"
private const val DELETE_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Deleted Money Exchange"
private const val INVALID_MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE = "[MoneyExchangeService][ERROR] Invalid Month Id or/and Exchange Id"
private const val EDIT_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Edit Money Exchange"
private const val EDIT_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Edited Money Exchange"
private const val GET_SUM_OF_MONEY_EXCHANGE_BY_SCOPE_AND_TYPE_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Get Sum Of Money Exchange By Scope And Type"
private const val GET_SUM_OF_MONEY_EXCHANGE_BY_SCOPE_AND_TYPE_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Sum Of Money Exchange By Scope And Type: "


/**
 * Service class for managing money exchanges.
 */
object MoneyExchangeService {

    /**
     * Adds a new money exchange.
     *
     * @param moneyExchange The money exchange to add.
     */
    fun addMoneyExchange(moneyExchange: MoneyExchange): Boolean{
        println(ADD_MONEY_EXCHANGE_ACTION_MESSAGE)
        MoneyExchangeStorage.saveMoneyExchange(moneyExchange)
        FireBaseService.updateUser()
        println(ADD_MONEY_EXCHANGE_RESULT_MESSAGE)
        return true
    }

    /**
     * Retrieves all money exchanges.
     *
     * @return A list of all money exchanges.
     */
    fun getAllMoneyExchanges(): List<MoneyExchange> {
        println(GET_ALL_MONEY_EXCHANGE_ACTION_MESSAGE)
        val allMoneyExchange = MoneyExchangeStorage.getAllMoneyExchange()
        println(GET_ALL_MONEY_EXCHANGE_RESULT_MESSAGE)
        return allMoneyExchange
    }

    /**
     * Retrieves a specific money exchange.
     *
     * @param monthAssociated The month associated with the money exchange.
     * @param exchange The index of the money exchange.
     * @return The requested money exchange.
     * @throws IllegalAccessError if the requested money exchange is not found.
     */
    fun getMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange {
        println(GET_MONEY_EXCHANGE_ACTION_MESSAGE)
        val moneyExchange = MoneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)
        moneyExchange?.let {
            println(MONEY_EXCHANGE_RESULT_MESSAGE)
            return it
        } ?:
            throw IllegalAccessError(INVALID_MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE)
    }

    /**
     * Edits a specific money exchange.
     *
     * @param monthAssociated The month associated with the money exchange.
     * @param exchange The index of the money exchange to edit.
     * @param updatedMoneyExchange The updated money exchange object.
     * @throws IllegalArgumentException if the requested money exchange is not found.
     */
    fun editMoneyExchange(
        monthAssociated: String,
        exchange: Int,
        updatedMoneyExchange: MoneyExchange
    ) {
        println(EDIT_MONEY_EXCHANGE_ACTION_MESSAGE)
        val oldExchange = MoneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)
        oldExchange?.let {
            it.value = updatedMoneyExchange.value
            it.type = updatedMoneyExchange.type
            it.scope = updatedMoneyExchange.scope
            it.description = updatedMoneyExchange.description
            println(EDIT_MONEY_EXCHANGE_RESULT_MESSAGE)
            MoneyExchangeStorage.editMoneyExchange(it)
            FireBaseService.updateUser()
        } ?:
            throw IllegalArgumentException(INVALID_MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE)
    }

    /**
     * Deletes a specific money exchange.
     *
     * @param monthAssociated The month associated with the money exchange.
     * @param exchange The index of the money exchange to delete.
     * @return The deleted money exchange.
     * @throws IllegalAccessError if the requested money exchange is not found.
     */
    fun deleteMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange {
        println(DELETE_MONEY_EXCHANGE_ACTION_MESSAGE)
        val deletedExchange =
            MoneyExchangeStorage.deleteMoneyExchange(monthAssociated, exchange)
        deletedExchange?.let {
            println(DELETE_MONEY_EXCHANGE_RESULT_MESSAGE)
            FireBaseService.updateUser()
            return it
        } ?:
        throw IllegalArgumentException(INVALID_MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE)
    }

    /**
     * Calculates the sum of money exchanges for a specific type and scope within a month.
     *
     * @param currentMonth The current month information.
     * @param currentType The type of money exchange to consider.
     * @param currentScope The scope of money exchange to consider.
     * @return The total sum of money exchanges for the specified type and scope.
     */
    fun getSumOfMoneyExchangeByScopeAndType(
        currentMonth: MonthInformation,
        currentType: MoneyExchangeType,
        currentScope: MoneyExchangeScope
    ): Double {
        println(GET_SUM_OF_MONEY_EXCHANGE_BY_SCOPE_AND_TYPE_ACTION_MESSAGE)
        val sumOf = currentMonth.summary.values
            .filter { acc -> acc.type == currentType }
            .filter { acc -> acc.scope == currentScope }
            .sumOf { acc -> acc.value }
        println(GET_SUM_OF_MONEY_EXCHANGE_BY_SCOPE_AND_TYPE_RESULT_MESSAGE + sumOf)
        return sumOf
    }
}
