package com.example.kala.entities

import java.time.LocalDate
import java.util.TreeMap

/**
 * Represents a collection of money exchanges for a specific month.
 *
 * @property id The unique identifier of the month (combination of month and year).
 * @property expensedMoney The total amount of money expended in the month.
 * @property incomeMoney The total amount of money earned in the month.
 * @property summary A TreeMap containing the money exchanges for the month.
 */
class MonthInformation {
    private val id: String
    var expensedMoney: Double = 0.0
    var incomeMoney: Double = 0.0
    val summary: TreeMap<Int, MoneyExchange> = TreeMap()
    val dateCreation: LocalDate = LocalDate.now()

    /**
     * Initializes the MonthInformation object and sets its ID based on the current month and year.
     */
    init {
        val month = dateCreation.month.toString()
        val year = dateCreation.year.toString()
        this.id = month + year
    }

    /**
     * Adds a new money exchange to the summary and updates the total expenditure or income accordingly.
     *
     * @param newMoneyExchange The new money exchange to add.
     */
    fun addMoneyExchange(newMoneyExchange: MoneyExchange): MoneyExchange {
        this.summary[newMoneyExchange.id] = newMoneyExchange

        when (newMoneyExchange.type) {
            MoneyExchangeType.EXPENSE -> expensedMoney += newMoneyExchange.value
            MoneyExchangeType.INCOME -> incomeMoney += newMoneyExchange.value
        }

        return newMoneyExchange
    }

    /**
     * Deletes a money exchange from the summary and updates the total expenditure or income accordingly.
     *
     * @param moneyExchange The money exchange to delete.
     * @return True if the money exchange was successfully deleted, false otherwise.
     */
    fun deleteMoneyExchange(moneyExchange: MoneyExchange): Boolean {
        val find = this.summary[moneyExchange.id]

        return find?.let {
            summary.remove(it.id)
            when (it.type) {
                MoneyExchangeType.EXPENSE -> expensedMoney -= it.value
                MoneyExchangeType.INCOME -> incomeMoney -= it.value
            }
            true
        } ?: false
    }
}
