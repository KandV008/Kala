package com.example.kala.entities

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MonthInformationTest{

    private lateinit var monthInformation: MonthInformation

    @Before
    fun onBefore(){
        this.monthInformation = MonthInformation()

        for (index in 1..10){
            val type: MoneyExchangeType = if (index % 2 == 0) MoneyExchangeType.EXPENSE
                                          else MoneyExchangeType.INCOME
            val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
            this.monthInformation.addMoneyExchange(MoneyExchange(index.toDouble(), type, scope))
        }
    }

    @Test
    fun `Get the data of the MonthInformation`(){
        val expectedExpense = 30
        val resultExpense = this.monthInformation.expensedMoney
        assertEquals(expectedExpense, resultExpense)

        val expectedIncome = 25
        val resultIncome = this.monthInformation.incomedMoney
        assertEquals(expectedIncome, resultIncome)

        val expectedSize = 10
        val resultSize = this.monthInformation.summary.size
        assertEquals(expectedSize, resultSize)
    }

    @Test
    fun `Delete a MoneyExchange from MonthInformation`(){
        val example1 = MoneyExchange(2.0, MoneyExchangeType.EXPENSE, MoneyExchangeScope.FOOD)
        var expected = true
        var result = this.monthInformation.deleteMoneyExchange(example1)
        assertEquals(expected, result)

        val example2 = MoneyExchange(11.0, MoneyExchangeType.INCOME, MoneyExchangeScope.OTHER)
        expected = false
        result = this.monthInformation.deleteMoneyExchange(example2)
        assertEquals(expected, result)

        val expectedSize = 9
        val resultSize = this.monthInformation.summary.size
        assertEquals(expectedSize, resultSize)
    }
}