package com.example.kala.entities

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MonthInformation {
    private val id: String
    var expensedMoney = 0
    var incomedMoney = 0
    val summary: ArrayList<MoneyExchange> = ArrayList()

    init {
        val date = LocalDate.now()
        val month = date.month.toString()
        val year = date.year.toString()
        this.id = month + year
    }
    fun addMoneyExchange(newMoneyExchange: MoneyExchange) {
        this.summary.add(newMoneyExchange)

        when (newMoneyExchange.type) {
            MoneyExchangeType.EXPENSE -> expensedMoney += newMoneyExchange.value
            MoneyExchangeType.INCOME -> incomedMoney += newMoneyExchange.value
        }
    }

    fun deleteMoneyExchange(moneyExchange: MoneyExchange) : Boolean{
        val find = this.summary.find { e -> e == moneyExchange }

        return find?.let {
            summary.remove(it)
            when (it.type) {
                MoneyExchangeType.EXPENSE -> expensedMoney -= it.value
                MoneyExchangeType.INCOME -> incomedMoney -= it.value
            }
            true
        } ?: false
    }
}