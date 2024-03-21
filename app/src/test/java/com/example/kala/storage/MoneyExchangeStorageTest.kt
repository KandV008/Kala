package com.example.kala.storage

import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for MoneyExchangeStorage class.
 */
class MoneyExchangeStorageTest {

    private lateinit var moneyExchange: MoneyExchange
    private lateinit var listMoneyExchange: MutableList<MoneyExchange>

    /**
     * Initializes test data before each test.
     */
    @Before
    fun onBefore(){
        val value = 30.0
        val type: MoneyExchangeType = MoneyExchangeType.EXPENSE
        val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
        val description = "Compra semanal"
        moneyExchange = MoneyExchange(value, type, scope, description)
        listMoneyExchange = mutableListOf(
            moneyExchange, moneyExchange, moneyExchange
        )
    }

    /**
     * Test case to add a new money exchange for the first time in a month.
     */
    @Test
    fun `Add a new money exchange for the first time in a month`() {
        val id = "${moneyExchange.date.month}${moneyExchange.date.year}"
        val expectedNumMonthInformation = MoneyExchangeStorage.getNumMonthInformation() + 1
        val expectedNumMoneyExchange = MoneyExchangeStorage.getNumMoneyExchangeFromMonthInformation(id) + 1

        MoneyExchangeStorage.saveMoneyExchange(moneyExchange)
        val resultNumMonthInformation = MoneyExchangeStorage.getNumMonthInformation()
        assertEquals(expectedNumMonthInformation, resultNumMonthInformation)

        val resultNumMoneyExchange = MoneyExchangeStorage.getNumMoneyExchangeFromMonthInformation(id)
        assertEquals(expectedNumMoneyExchange, resultNumMoneyExchange)
    }

    /**
     * Test case to add a new money exchange.
     */
    @Test
    fun `Add a new money exchange`() {
        val id = "${moneyExchange.date.month}${moneyExchange.date.year}"
        val expectedNumMonthInformation = MoneyExchangeStorage.getNumMonthInformation()
        val expectedNumMoneyExchange = MoneyExchangeStorage.getNumMoneyExchangeFromMonthInformation(id) + 1

        MoneyExchangeStorage.saveMoneyExchange(moneyExchange)
        val resultNumMonthInformation = MoneyExchangeStorage.getNumMonthInformation()
        assertEquals(expectedNumMonthInformation, resultNumMonthInformation)

        val resultNumMoneyExchange = MoneyExchangeStorage.getNumMoneyExchangeFromMonthInformation(id)
        assertEquals(expectedNumMoneyExchange, resultNumMoneyExchange)
    }

    /**
     * Test case to get all money exchanges.
     */
    @Test
    fun getAllMoneyExchange() {
        val expectedNumMoneyExchange = listMoneyExchange.size
        val resultNumMoneyExchange = MoneyExchangeStorage.getAllMoneyExchange().size

        assertEquals(expectedNumMoneyExchange, resultNumMoneyExchange)
    }

    /**
     * Test case to get a specific money exchange.
     */
    @Test
    fun getMoneyExchange() {
        val idMonth = "${moneyExchange.date.month}${moneyExchange.date.year}"
        val idExchange = 0
        val resultMoneyExchange = MoneyExchangeStorage.getMoneyExchange(idMonth, idExchange)

        assertNull(resultMoneyExchange)
    }
}
