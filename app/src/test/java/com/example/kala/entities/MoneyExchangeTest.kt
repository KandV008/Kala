package com.example.kala.entities

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
class MoneyExchangeTest{

    private lateinit var moneyExchange: MoneyExchange
    private val listMoneyExchange: ArrayList<MoneyExchange> = ArrayList();

    @Before
    fun onBefore(){
        val value = 30
        val type: MoneyExchangeType = MoneyExchangeType.EXPENSE
        val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
        val description = "Compra semanal"
        moneyExchange = MoneyExchange(value, type, scope, description)

        for (index in 1..4)
            listMoneyExchange.add(moneyExchange)
    }

    @Test
    fun `Create a New Money Exchange`(){
        val value = 15
        val type: MoneyExchangeType = MoneyExchangeType.INCOME
        val scope: MoneyExchangeScope = MoneyExchangeScope.OTHER
        val newMoneyExchange = MoneyExchange(value, type, scope)
        assertNotNull(newMoneyExchange)
    }

    @Test
    fun  `Read a Money Exchange`(){
        val expectedValue = 30
        assertEquals(expectedValue, moneyExchange.value)

        val expectedType: MoneyExchangeType = MoneyExchangeType.EXPENSE
        assertEquals(expectedType, moneyExchange.type)

        val expectedScope: MoneyExchangeScope = MoneyExchangeScope.FOOD
        assertEquals(expectedScope, moneyExchange.scope)

        assertNotNull(moneyExchange.description)
        assertNotNull(moneyExchange.date)
    }

    @Test
    fun `Edit a Money Exchange`(){
        val newValue = 50
        moneyExchange.value = newValue
        val expectedValue = 50
        assertEquals(expectedValue, moneyExchange.value)
    }

    @Test
    fun `Delete a Money Exchange from an array`(){
        val startValue = listMoneyExchange.size
        listMoneyExchange.removeAt(0)
        val endValue = listMoneyExchange.size
        val expected = 1
        assertEquals(expected, startValue-endValue)
    }
}