package com.example.kala.model.storage

import com.example.kala.model.entities.MoneyExchange
import com.example.kala.model.entities.MonthInformation
import java.util.TreeMap

private const val SAVE_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Save Money Exchange"
private const val CREATE_NEW_MONTH_INFORMATION_ADVICE_MESSAGE = "[MoneyExchangeStorage][ADVICE] Creating a new Month Information"
private const val SAVE_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] "
private const val GET_NUM_MONTH_INFORMATION_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Get Num of Month Information"
private const val GET_NUM_MONTH_INFORMATION_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] Num: "
private const val GET_NUM_MONEY_EXCHANGE_FROM_MONTH_INFORMATION_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Get Num Money Exchanges From Month Information"
private const val GET_NUM_MONEY_EXCHANGE_FROM_MONTH_INFORMATION_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] Num: "
private const val GET_ALL_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Get All Money Exchanges"
private const val GET_ALL_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] Num of all money exchanges: "
private const val GET_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Get Money Exchange"
private const val GET_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] "
private const val DELETE_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Delete Money Exchange"
private const val DELETE_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] "
private const val EDIT_MONEY_EXCHANGE_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Edit Money Exchange"
private const val EDIT_MONEY_EXCHANGE_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] "
private const val GET_MONTH_INFORMATION_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Get Month Information"
private const val GET_MONTH_INFORMATION_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] "
private const val EXIST_MONTH_INFORMATION_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Exist Month Information"
private const val EXIST_MONTH_INFORMATION_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] "
private const val CLEAN_STORAGE_ACTION_MESSAGE = "[MoneyExchangeStorage][ACTION] Clean Storage"
private const val CLEAN_STORAGE_RESULT_MESSAGE = "[MoneyExchangeStorage][RESULT] Size of the Storage: "


/**
 * Storage class for managing money exchange data.
 */
object MoneyExchangeStorage {
    var monthInformationMap: TreeMap<String, MonthInformation> = TreeMap()

    /**
     * Saves a money exchange.
     *
     * @param moneyExchange The money exchange to be saved.
     * @return The month information after saving the money exchange.
     */
    fun saveMoneyExchange(moneyExchange: MoneyExchange): MoneyExchange {
        println(SAVE_MONEY_EXCHANGE_ACTION_MESSAGE)
        val idMonth = "${moneyExchange.date.month}${moneyExchange.date.year}"

        monthInformationMap.getOrPut(idMonth) {
            println(CREATE_NEW_MONTH_INFORMATION_ADVICE_MESSAGE)
            MonthInformation()
        }.apply {
            val idExchange = this.summary.size
            moneyExchange.id = idExchange
            moneyExchange.monthAssociated = idMonth
            val newMoneyExchange = addMoneyExchange(moneyExchange)
            println(SAVE_MONEY_EXCHANGE_RESULT_MESSAGE + newMoneyExchange)
            return newMoneyExchange
        }
    }

    /**
     * Gets the number of month information entries.
     *
     * @return The number of month information entries.
     */
    fun getNumMonthInformation(): Int {
        println(GET_NUM_MONTH_INFORMATION_ACTION_MESSAGE)
        val size = this.monthInformationMap.size
        println(GET_NUM_MONTH_INFORMATION_RESULT_MESSAGE + size)
        return size
    }

    /**
     * Gets the number of money exchanges from a specific month information.
     *
     * @param id The identifier of the month.
     * @return The number of money exchanges from the specified month.
     */
    fun getNumMoneyExchangeFromMonthInformation(id: String): Int {
        println(GET_NUM_MONEY_EXCHANGE_FROM_MONTH_INFORMATION_ACTION_MESSAGE)
        val size = this.monthInformationMap[id]?.summary?.size ?: 0
        println(GET_NUM_MONEY_EXCHANGE_FROM_MONTH_INFORMATION_RESULT_MESSAGE + size)
        return size
    }

    /**
     * Gets all money exchanges from all months.
     *
     * @return List of all money exchanges.
     */
    fun getAllMoneyExchange(): List<MoneyExchange> {
        println(GET_ALL_MONEY_EXCHANGE_ACTION_MESSAGE)
        val list: MutableList<MoneyExchange> = mutableListOf()
        val allMonths = this.monthInformationMap.values

        allMonths.forEach{
            list.addAll(it.summary.values.reversed())
        }

        println(GET_ALL_MONEY_EXCHANGE_RESULT_MESSAGE + list.size)
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
        println(GET_MONEY_EXCHANGE_ACTION_MESSAGE)
        val moneyExchange = this.monthInformationMap[monthAssociated]?.summary?.get(exchange.toString())
        println(GET_MONEY_EXCHANGE_RESULT_MESSAGE + moneyExchange)
        return moneyExchange
    }

    /**
     * Deletes a specific money exchange from a month.
     *
     * @param monthAssociated The month associated with the money exchange.
     * @param exchange The index of the money exchange.
     * @return The deleted money exchange object, or null if not found.
     */
    fun deleteMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange? {
        println(DELETE_MONEY_EXCHANGE_ACTION_MESSAGE)
        val moneyExchange = this.getMoneyExchange(monthAssociated, exchange)
        return moneyExchange?.let {
            this.monthInformationMap[monthAssociated]?.deleteMoneyExchange(
                it
            )
            println(DELETE_MONEY_EXCHANGE_RESULT_MESSAGE + it)
            return it
        }
    }

    /**
     * Edits a specific money exchange.
     *
     * @param moneyExchange The updated money exchange.
     */
    fun editMoneyExchange(moneyExchange: MoneyExchange): MoneyExchange {
        println(EDIT_MONEY_EXCHANGE_ACTION_MESSAGE)
        this.monthInformationMap[moneyExchange.monthAssociated]?.addMoneyExchange(moneyExchange)
        println(EDIT_MONEY_EXCHANGE_RESULT_MESSAGE + moneyExchange)
        return moneyExchange
    }

    /**
     * Retrieves information about a specific month from the storage.
     *
     * If the month information does not exist, a new one is created.
     *
     * @param id The unique identifier of the month.
     * @return The information about the specified month.
     */
    fun getMonthInformation(id: String): MonthInformation {
        println(GET_MONTH_INFORMATION_ACTION_MESSAGE)
        val monthInformation = this.monthInformationMap[id]
        monthInformation?.let {
            println(GET_MONTH_INFORMATION_RESULT_MESSAGE + it)
            return it
        } ?: run {
            println(CREATE_NEW_MONTH_INFORMATION_ADVICE_MESSAGE)
            return createMonthInformation(id)
        }
    }

    /**
     * Creates a new month information entry.
     *
     * @param id The unique identifier of the month.
     * @return The newly created month information.
     */
    private fun createMonthInformation(id: String): MonthInformation {
        val newMonthInformation = MonthInformation()
        this.monthInformationMap[id] = newMonthInformation
        return newMonthInformation
    }

    /**
     * Checks if month information with the given ID exists.
     *
     * @param idMonth The unique identifier of the month.
     * @return `true` if month information exists, `false` otherwise.
     */
    fun existMonthInformation(idMonth: String): Boolean {
        println(EXIST_MONTH_INFORMATION_ACTION_MESSAGE)
        val containsKey = this.monthInformationMap.containsKey(idMonth)
        println(EXIST_MONTH_INFORMATION_RESULT_MESSAGE + containsKey)
        return containsKey
    }

    fun clean() {
        println(CLEAN_STORAGE_ACTION_MESSAGE)
        this.monthInformationMap.clear()
        println(CLEAN_STORAGE_RESULT_MESSAGE +  this.monthInformationMap.size)
    }

}
