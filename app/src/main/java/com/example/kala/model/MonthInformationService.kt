package com.example.kala.model

import com.example.kala.model.entities.MonthInformation
import com.example.kala.model.storage.MoneyExchangeStorage
import java.util.TreeMap

private const val GET_MONTH_INFORMATION_ACTION_MESSAGE = "[MonthInformationService][ACTION] Get Month Information"
private const val GET_MONTH_INFORMATION_RESULT_MESSAGE = "[MonthInformationService][RESULT] Month id: "
private const val HAS_NEXT_MONTH_ACTION_MESSAGE = "[MonthInformationService][ACTION] Has Next Month"
private const val HAS_NEXT_MONTH_RESULT_MESSAGE = "[MonthInformationService][RESULT] Exist next month: "
private const val HAS_PREV_MONTH_ACTION_MESSAGE = "[MonthInformationService][ACTION] Has Prev Month"
private const val HAS_PREV_MONTH_RESULT_MESSAGE = "[MonthInformationService][RESULT] Exist prev month: "
private const val GET_NEXT_MONTH_ACTION_MESSAGE = "[MonthInformationService][ACTION] Get Next Month"
private const val GET_NEXT_MONTH_RESULT_MESSAGE = "[MonthInformationService][RESULT] Next Month Id: "
private const val GET_PREV_MONTH_ACTION_MESSAGE = "[MonthInformationService][ACTION] Get Prev Month"
private const val GET_PREV_MONTH_RESULT_MESSAGE = "[MonthInformationService][RESULT] Prev Month Id: "
private const val CLEAN_STORAGE_ACTION_MESSAGE = "[MonthInformationService][ACTION] Clean Storage"
private const val CLEAN_STORAGE_RESULT_MESSAGE = "[MonthInformationService][RESULT] Storage cleaned "

object MonthInformationService {

    fun addMonthInformation(monthInformation: MonthInformation) {
        MoneyExchangeStorage.monthInformationMap[monthInformation.id] = monthInformation
    }

    fun getAllMonthInformation(): TreeMap<String, MonthInformation> {
        return MoneyExchangeStorage.monthInformationMap
    }

    /**
     * Retrieves information about a specific month from the storage.
     *
     * @param id The unique identifier of the month.
     * @return The information about the specified month.
     */
    fun getMonthInformation(id: String): MonthInformation {
        println(GET_MONTH_INFORMATION_ACTION_MESSAGE)
        val monthInformation = MoneyExchangeStorage.getMonthInformation(id)
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
        val hasNextMonth: Boolean = MoneyExchangeStorage.existMonthInformation(idMonth)
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
        val hasPrevMonth: Boolean = MoneyExchangeStorage.existMonthInformation(idMonth)
        println(HAS_PREV_MONTH_RESULT_MESSAGE + hasPrevMonth)
        return if (hasPrevMonth) 1 else 0
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

    fun clean() {
        println(CLEAN_STORAGE_ACTION_MESSAGE)
        MoneyExchangeStorage.clean()
        println(CLEAN_STORAGE_RESULT_MESSAGE)
    }

}