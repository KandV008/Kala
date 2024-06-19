package com.example.kala.model.entities

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
class MonthInformation() {
    var id: String
    var expensedMoney: Double = 0.0
    var incomeMoney: Double = 0.0
    val summary: TreeMap<String, MoneyExchange> = TreeMap()
    var dateCreation: LocalDate = LocalDate.now()

    constructor(id: String, expensedMoney: Double, incomeMoney: Double, dateCreation: String) : this() {
        this.id = id
        this.expensedMoney = expensedMoney
        this.incomeMoney = incomeMoney
        this.dateCreation = LocalDate.parse(dateCreation)
    }

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
        this.summary[newMoneyExchange.id.toString()] = newMoneyExchange

        expensedMoney = summary.values
            .filter { it.type == MoneyExchangeType.EXPENSE }
            .sumOf { it.value }

        incomeMoney = summary.values
            .filter { it.type == MoneyExchangeType.INCOME }
            .sumOf { it.value }

        return newMoneyExchange
    }

    /**
     * Deletes a money exchange from the summary and updates the total expenditure or income accordingly.
     *
     * @param moneyExchange The money exchange to delete.
     * @return True if the money exchange was successfully deleted, false otherwise.
     */
    fun deleteMoneyExchange(moneyExchange: MoneyExchange): Boolean {
        val find = this.summary[moneyExchange.id.toString()]

        return find?.let {
            summary.remove(it.id.toString())
            when (it.type) {
                MoneyExchangeType.EXPENSE -> expensedMoney -= it.value
                MoneyExchangeType.INCOME -> incomeMoney -= it.value
            }
            true
        } ?: false
    }

    override fun toString(): String {
        return "Month Information with the id: $id\n" +
                "Date Creation = $dateCreation; Size of money exchange associated = ${summary.size}; " +
                "Total expense money = $expensedMoney; Total income money = $incomeMoney"
    }

    fun monthInformationToMap(): Map<String, Any> {
        return mapOf(
            "id" to this.id,
            "expensedMoney" to this.expensedMoney,
            "incomeMoney" to this.incomeMoney,
            "summary" to this.summary.mapValues { it.value.toMap() },
            "dateCreation" to this.dateCreation.toString()
        )
    }

    private fun MoneyExchange.toMap(): Map<String, Any> {
        return mapOf(
            "value" to this.value,
            "type" to this.type.name,
            "scope" to this.scope.name,
            "description" to (if (this.description != null) this.description.toString() else ""),
            "id" to this.id,
            "monthAssociated" to this.monthAssociated,
            "date" to this.date.toString()
        )
    }
}