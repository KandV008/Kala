package com.example.kala.model

import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.entities.MonthInformation
import com.example.kala.storage.MoneyExchangeStorage

/**
 * Service class for managing money exchanges.
 */
object MoneyExchangeService {

    private val moneyExchangeStorage = MoneyExchangeStorage()

    /**
     * Adds a new money exchange.
     *
     * @param moneyExchange The money exchange to add.
     */
    fun addMoneyExchange(moneyExchange: MoneyExchange): Boolean{
        // TODO: Service validation

        moneyExchangeStorage.saveMoneyExchange(moneyExchange)
        return true
    }

    /**
     * Retrieves all money exchanges.
     *
     * @return A list of all money exchanges.
     */
    fun getAllMoneyExchanges(): List<MoneyExchange> {
        return moneyExchangeStorage.getAllMoneyExchange().reversed()
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
        val moneyExchange = moneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)

        moneyExchange?.let {
            return it.copy()
        } ?:
            throw IllegalAccessError() // TODO: Handle error
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
        val deletedExchange =
            this.moneyExchangeStorage.deleteMoneyExchange(monthAssociated, exchange)

        deletedExchange?.let {
            return it
        } ?:
         throw IllegalArgumentException() //TODO Handle Error
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
        val oldExchange = this.moneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)

        oldExchange?.let {
            it.value = updatedMoneyExchange.value
            it.type = updatedMoneyExchange.type
            it.scope = updatedMoneyExchange.scope
            it.description = updatedMoneyExchange.description

            this.moneyExchangeStorage.editMoneyExchange(it)
        } ?:
            throw IllegalArgumentException() //TODO Handle Error
    }

    fun getMonthInformation(id: String): MonthInformation {
        return this.moneyExchangeStorage.getMonthInformation(id)
    }

    fun hasNextMonth(currentMonth: MonthInformation): Int {
        val nextMonth = currentMonth.dateCreation.plusMonths(1L)
        val idMonth = "${nextMonth.month}${nextMonth.year}"

        val hasNextMonth: Boolean = this.moneyExchangeStorage.existMonthInformation(idMonth)
        return hasNextMonth.compareTo(true)
    }

    fun hasPrevMonth(currentMonth: MonthInformation): Int {
        val prevMonth = currentMonth.dateCreation.plusMonths(1L)
        val idMonth = "${prevMonth.month}${prevMonth.year}"

        val hasPrevMonth: Boolean = this.moneyExchangeStorage.existMonthInformation(idMonth)
        return hasPrevMonth.compareTo(true)
    }

    fun getSumOfMoneyExchangeByScopeAndType(
        currentMonth: MonthInformation,
        currentType: MoneyExchangeType,
        currentScope: MoneyExchangeScope
    ): Double {
        return currentMonth.summary.values
            .filter { acc -> acc.type == currentType }
            .filter { acc -> acc.scope == currentScope }
            .sumOf { acc -> acc.value }
    }
}
