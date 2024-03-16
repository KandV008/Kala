package com.example.kala.storage

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MonthInformation

object MoneyExchangeStorage {
    private val monthInformationMap: MutableMap<String, MonthInformation> = mutableMapOf()

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveMoneyExchange(moneyExchange: MoneyExchange): MonthInformation? {
        val id = "${moneyExchange.date.month}${moneyExchange.date.year}"

        return monthInformationMap.getOrPut(id) { MonthInformation() }.apply {
            addMoneyExchange(moneyExchange)
        }
    }

    fun getNumMonthInformation(): Int {
        return this.monthInformationMap.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNumMoneyExchangeFromMonthInformation(id: String): Int {
        return this.monthInformationMap[id]?.summary?.size ?: 0
    }
}
