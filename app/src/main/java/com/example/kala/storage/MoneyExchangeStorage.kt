package com.example.kala.storage

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.entities.MonthInformation

/**
 * Storage class for managing money exchange data.
 */
@RequiresApi(Build.VERSION_CODES.O)
object MoneyExchangeStorage {
    private val monthInformationMap: MutableMap<String, MonthInformation> = mutableMapOf()

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
    @RequiresApi(Build.VERSION_CODES.O)
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNumMoneyExchangeFromMonthInformation(id: String): Int {
        return this.monthInformationMap[id]?.summary?.size ?: 0
    }

    /**
     * Gets all money exchanges from all months.
     *
     * @return List of all money exchanges.
     */
    @RequiresApi(Build.VERSION_CODES.O)
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange? {
        return this.monthInformationMap[monthAssociated]?.summary?.get(exchange)
    }
}
