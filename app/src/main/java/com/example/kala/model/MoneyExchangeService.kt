package com.example.kala.model

import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.entities.MonthInformation
import com.example.kala.storage.MoneyExchangeStorage
import com.google.firebase.auth.FirebaseAuth

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
private const val GET_MONTH_INFORMATION_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Get Month Information"
private const val GET_MONTH_INFORMATION_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Month id: "
private const val HAS_NEXT_MONTH_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Has Next Month"
private const val HAS_NEXT_MONTH_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Exist next month: "
private const val HAS_PREV_MONTH_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Has Prev Month"
private const val HAS_PREV_MONTH_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Exist prev month: "
private const val GET_SUM_OF_MONEY_EXCHANGE_BY_SCOPE_AND_TYPE_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Get Sum Of Money Exchange By Scope And Type"
private const val GET_SUM_OF_MONEY_EXCHANGE_BY_SCOPE_AND_TYPE_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Sum Of Money Exchange By Scope And Type: "
private const val GET_NEXT_MONTH_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Get Next Month"
private const val GET_NEXT_MONTH_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Next Month Id: "
private const val GET_PREV_MONTH_ACTION_MESSAGE = "[MoneyExchangeService][ACTION] Get Prev Month"
private const val GET_PREV_MONTH_RESULT_MESSAGE = "[MoneyExchangeService][RESULT] Prev Month Id: "

/**
 * Service class for managing money exchanges.
 */
object MoneyExchangeService {

    val moneyExchangeStorage = MoneyExchangeStorage()

    private fun updateDataBase() {
        FirebaseAuth.getInstance().currentUser?.email?.let {
            FireBaseService.saveUser(it)
        }
    }

    fun addMonthInformation(monthInformation: MonthInformation) {
        moneyExchangeStorage.monthInformationMap[monthInformation.id] = monthInformation
    }

    /**
     * Adds a new money exchange.
     *
     * @param moneyExchange The money exchange to add.
     */
    fun addMoneyExchange(moneyExchange: MoneyExchange): Boolean{
        println(ADD_MONEY_EXCHANGE_ACTION_MESSAGE)
        this.moneyExchangeStorage.saveMoneyExchange(moneyExchange)
        this.updateDataBase()
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
        val allMoneyExchange = moneyExchangeStorage.getAllMoneyExchange()
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
        val moneyExchange = moneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)
        moneyExchange?.let {
            println(MONEY_EXCHANGE_RESULT_MESSAGE)
            return it.copy()
        } ?:
            throw IllegalAccessError(INVALID_MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE)
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
            this.moneyExchangeStorage.deleteMoneyExchange(monthAssociated, exchange)
        deletedExchange?.let {
            println(DELETE_MONEY_EXCHANGE_RESULT_MESSAGE)
            return it
        } ?:
         throw IllegalArgumentException(INVALID_MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE)
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
        val oldExchange = this.moneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)
        oldExchange?.let {
            it.value = updatedMoneyExchange.value
            it.type = updatedMoneyExchange.type
            it.scope = updatedMoneyExchange.scope
            it.description = updatedMoneyExchange.description
            println(EDIT_MONEY_EXCHANGE_RESULT_MESSAGE)
            this.moneyExchangeStorage.editMoneyExchange(it)
            this.updateDataBase()
        } ?:
            throw IllegalArgumentException(INVALID_MONTH_ID_OR_EXCHANGE_ID_ERROR_MESSAGE)
    }

    /**
     * Retrieves information about a specific month from the storage.
     *
     * @param id The unique identifier of the month.
     * @return The information about the specified month.
     */
    fun getMonthInformation(id: String): MonthInformation {
        println(GET_MONTH_INFORMATION_ACTION_MESSAGE)
        val monthInformation = this.moneyExchangeStorage.getMonthInformation(id)
        println(GET_MONTH_INFORMATION_RESULT_MESSAGE + monthInformation)
        return monthInformation
    }

    /**
     * Checks if there is a next month available in the storage.
     *
     * @param currentMonth The current month information.
     * @return `1` if there is a next month, `0` otherwise.
     */
    fun hasNextMonth(currentMonth: MonthInformation): Int {
        println(HAS_NEXT_MONTH_ACTION_MESSAGE)
        val nextMonth = currentMonth.dateCreation.plusMonths(1L)
        val idMonth = "${nextMonth.month}${nextMonth.year}"
        val hasNextMonth: Boolean = this.moneyExchangeStorage.existMonthInformation(idMonth)
        println(HAS_NEXT_MONTH_RESULT_MESSAGE + hasNextMonth)
        return if (hasNextMonth) 1 else 0

    }

    /**
     * Checks if there is a previous month available in the storage.
     *
     * @param currentMonth The current month information.
     * @return `1` if there is a previous month, `0` otherwise.
     */
    fun hasPrevMonth(currentMonth: MonthInformation): Int {
        println(HAS_PREV_MONTH_ACTION_MESSAGE)
        val prevMonth = currentMonth.dateCreation.plusMonths(-1L)
        val idMonth = "${prevMonth.month}${prevMonth.year}"
        val hasPrevMonth: Boolean = this.moneyExchangeStorage.existMonthInformation(idMonth)
        println(HAS_PREV_MONTH_RESULT_MESSAGE + hasPrevMonth)
        return if (hasPrevMonth) 1 else 0
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

    /**
     * Get the next month in the storage.
     *
     * @param currentMonth The current month information.
     * @return Next Month Information.
     */
    fun getNextMonth(currentMonth: MonthInformation): String {
        println(GET_NEXT_MONTH_ACTION_MESSAGE)
        val nextMonth = currentMonth.dateCreation.plusMonths(1L)
        val idMonth = "${nextMonth.month}${nextMonth.year}"
        println(GET_NEXT_MONTH_RESULT_MESSAGE + idMonth)
        return idMonth
    }

    /**
     * Get the previous month in the storage.
     *
     * @param currentMonth The current month information.
     * @return Previous Month Information.
     */
    fun getPrevMonth(currentMonth: MonthInformation): String {
        println(GET_PREV_MONTH_ACTION_MESSAGE)
        val prevMonth = currentMonth.dateCreation.plusMonths(-1L)
        val idMonth = "${prevMonth.month}${prevMonth.year}"
        println(GET_PREV_MONTH_RESULT_MESSAGE + idMonth)
        return idMonth
    }

}
