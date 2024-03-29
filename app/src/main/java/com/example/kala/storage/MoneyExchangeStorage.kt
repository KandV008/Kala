package com.example.kala.storage

import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.entities.MonthInformation
import java.util.TreeMap

/**
 * Storage class for managing money exchange data.
 */
class MoneyExchangeStorage {
    private val monthInformationMap: TreeMap<String, MonthInformation> = TreeMap()

    init {
        // Initialize with an example month information
        val exampleMonthInformation = MonthInformation()
        val exampleMoneyExchange =
            MoneyExchange(10.0, MoneyExchangeType.EXPENSE, MoneyExchangeScope.FOOD, "example")
        exampleMoneyExchange.id = 0
        exampleMoneyExchange.monthAssociated = "example"
        exampleMonthInformation.summary[exampleMoneyExchange.id] = exampleMoneyExchange
        this.monthInformationMap["example"] = exampleMonthInformation
    }

    /**
     * Saves a money exchange.
     *
     * @param moneyExchange The money exchange to be saved.
     * @return The month information after saving the money exchange.
     */
    fun saveMoneyExchange(moneyExchange: MoneyExchange): MonthInformation {
        val idMonth = "${moneyExchange.date.month}${moneyExchange.date.year}"

        return monthInformationMap.getOrPut(idMonth) { MonthInformation() }.apply {
            val idExchange = this.summary.size
            moneyExchange.id = idExchange
            moneyExchange.monthAssociated = idMonth
            addMoneyExchange(moneyExchange)
        }
    }

    /**
     * Gets the number of month information entries.
     *
     * @return The number of month information entries.
     */
    fun getNumMonthInformation(): Int {
        return this.monthInformationMap.size
    }

    /**
     * Gets the number of money exchanges from a specific month information.
     *
     * @param id The identifier of the month.
     * @return The number of money exchanges from the specified month.
     */
    fun getNumMoneyExchangeFromMonthInformation(id: String): Int {
        return this.monthInformationMap[id]?.summary?.size ?: 0
    }

    /**
     * Gets all money exchanges from all months.
     *
     * @return List of all money exchanges.
     */
    fun getAllMoneyExchange(): List<MoneyExchange> {
        val list: MutableList<MoneyExchange> = mutableListOf()
        val allMonths = this.monthInformationMap.values

        allMonths.forEach{
            list.addAll(it.summary.values)
        }

        return list
    }

    /**
     * Gets a specific money exchange from a month.
     *
     * @param monthAssociated The month associated with the money exchange.
     * @param exchange The index of the money exchange.
     * @return The money exchange object, or null if not found.
     */
    fun getMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange? {
        return this.monthInformationMap[monthAssociated]?.summary?.get(exchange)
    }

    /**
     * Deletes a specific money exchange from a month.
     *
     * @param monthAssociated The month associated with the money exchange.
     * @param exchange The index of the money exchange.
     * @return The deleted money exchange object, or null if not found.
     */
    fun deleteMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange? {
        val moneyExchange = this.getMoneyExchange(monthAssociated, exchange)
        return moneyExchange?.let {
            this.monthInformationMap[monthAssociated]?.deleteMoneyExchange(
                it
            )

            return it
        }
    }

    /**
     * Edits a specific money exchange.
     *
     * @param moneyExchange The updated money exchange.
     */
    fun editMoneyExchange(moneyExchange: MoneyExchange) {
        this.monthInformationMap[moneyExchange.monthAssociated]?.summary?.set(moneyExchange.id,
            moneyExchange
        )
    }

    fun getMonthInformation(id: String): MonthInformation {
        return this.monthInformationMap[id]!!
    }
}
