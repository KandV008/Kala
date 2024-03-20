package com.example.kala.storage

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.entities.MonthInformation

@RequiresApi(Build.VERSION_CODES.O)
object MoneyExchangeStorage {
    @RequiresApi(Build.VERSION_CODES.O)
    private val monthInformationMap: MutableMap<String, MonthInformation> = mutableMapOf()
    init {
        val exampleMonthInformation = MonthInformation()
        val exampleMoneyExchange =
            MoneyExchange(10.0, MoneyExchangeType.EXPENSE, MoneyExchangeScope.FOOD, "example")
        exampleMoneyExchange.id = 0
        exampleMoneyExchange.monthAssociated = "example"
        exampleMonthInformation.summary.add(exampleMoneyExchange)
        this.monthInformationMap["example"] = exampleMonthInformation
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveMoneyExchange(moneyExchange: MoneyExchange): MonthInformation? {
        val idMonth = "${moneyExchange.date.month}${moneyExchange.date.year}"

        return monthInformationMap.getOrPut(idMonth) { MonthInformation() }.apply {
            val idExchange = this.summary.size
            moneyExchange.id = idExchange
            moneyExchange.monthAssociated = idMonth
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllMoneyExchange(): List<MoneyExchange> {
        val list: MutableList<MoneyExchange> = mutableListOf()
        val allMonths = this.monthInformationMap.values

        allMonths.forEach{
            list.addAll(it.summary)
        }

        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange? {
        return this.monthInformationMap[monthAssociated]?.summary?.get(exchange)
    }
}
