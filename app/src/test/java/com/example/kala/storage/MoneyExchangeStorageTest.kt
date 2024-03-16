package com.example.kala.storage

import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MoneyExchangeStorageTest {

    private lateinit var moneyExchange: MoneyExchange

    @Before
    fun onBefore(){
        val value = 30.0
        val type: MoneyExchangeType = MoneyExchangeType.EXPENSE
        val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
        val description = "Compra semanal"
        moneyExchange = MoneyExchange(value, type, scope, description)
    }

    @Test
    fun `Add a new money exchange for the first time`() {
        MoneyExchangeStorage.saveMoneyExchange(moneyExchange)
        val id = "${moneyExchange.date.month}${moneyExchange.date.year}"

        val expectedNumMonthInformation = 1
        val resultNumMonthInformation = MoneyExchangeStorage.getNumMonthInformation()
        assertEquals(expectedNumMonthInformation, resultNumMonthInformation)

        val expectedNumMoneyExchange = 1
        val resultNumMoneyExchange = MoneyExchangeStorage.getNumMoneyExchangeFromMonthInformation(id)
        assertEquals(expectedNumMoneyExchange, resultNumMoneyExchange)
    }

    @Test
    fun `Add a new money exchange`() {
        MoneyExchangeStorage.saveMoneyExchange(moneyExchange)
        MoneyExchangeStorage.saveMoneyExchange(moneyExchange)
        val id = "${moneyExchange.date.month}${moneyExchange.date.year}"

        val expectedNumMonthInformation = 1
        val resultNumMonthInformation = MoneyExchangeStorage.getNumMonthInformation()
        assertEquals(expectedNumMonthInformation, resultNumMonthInformation)

        val expectedNumMoneyExchange = 2
        val resultNumMoneyExchange = MoneyExchangeStorage.getNumMoneyExchangeFromMonthInformation(id)
        assertEquals(expectedNumMoneyExchange, resultNumMoneyExchange)
    }
}