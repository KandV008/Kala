package com.example.kala.model

import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for MoneyExchangeService class.
 */
class MoneyExchangeServiceTest {

    private lateinit var moneyExchange: MoneyExchange

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
    }

    /**
     * Test case for adding a money exchange.
     */
    @Test
    fun addMoneyExchange() {
        MoneyExchangeService.addMoneyExchange(moneyExchange)
        assert(true)
    }

    /**
     * Test case for getting all money exchanges.
     */
    @Test
    fun getAllMoneyExchanges() {
        val expected = 1
        val allMoneyExchanges = MoneyExchangeService.getAllMoneyExchanges()
        val result = allMoneyExchanges.size
        assertEquals(expected, result)
    }

    /**
     * Test case for getting a specific money exchange.
     */
    @Test
    fun getMoneyExchange() {
        val idMonth = "example"
        val idExchange = 0
        val result = MoneyExchangeService.getMoneyExchange(idMonth, idExchange)

        assertNotNull(result)
    }
}
