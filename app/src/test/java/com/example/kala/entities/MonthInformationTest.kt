package com.example.kala.entities

import com.example.kala.model.entities.MoneyExchange
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.model.entities.MonthInformation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for MonthInformation class.
 */
class MonthInformationTest {

    private lateinit var monthInformation: MonthInformation

    /**
     * Initializes test data before each test.
     */
    @Before
    fun onBefore() {
        this.monthInformation = MonthInformation()

        // Adding 10 MoneyExchange objects to monthInformation for testing
        for (index in 1..10) {
            val type: MoneyExchangeType = if (index % 2 == 0) MoneyExchangeType.EXPENSE
            else MoneyExchangeType.INCOME
            val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
            this.monthInformation.addMoneyExchange(MoneyExchange(index.toDouble(), type, scope))
        }
    }

    /**
     * Test case for getting the data of the MonthInformation.
     */
    @Test
    fun `Get the data of the MonthInformation`() {
        val expectedExpense = 30.0
        val resultExpense = this.monthInformation.expensedMoney
        assertEquals(expectedExpense, resultExpense, 0.0)

        val expectedIncome = 25.0
        val resultIncome = this.monthInformation.incomeMoney
        assertEquals(expectedIncome, resultIncome, 0.0)

        val expectedSize = 10
        val resultSize = this.monthInformation.summary.size
        assertEquals(expectedSize, resultSize)
    }

    /**
     * Test case for deleting a MoneyExchange from MonthInformation.
     */
    @Test
    fun `Delete a MoneyExchange from MonthInformation`() {
        // Deleting an existing MoneyExchange
        val example1 = MoneyExchange(2.0, MoneyExchangeType.EXPENSE, MoneyExchangeScope.FOOD)
        var expected = true
        var result = this.monthInformation.deleteMoneyExchange(example1)
        assertEquals(expected, result)

        // Deleting a non-existing MoneyExchange
        val example2 = MoneyExchange(11.0, MoneyExchangeType.INCOME, MoneyExchangeScope.OTHER)
        expected = false
        result = this.monthInformation.deleteMoneyExchange(example2)
        assertEquals(expected, result)

        // Checking if the size of summary decreases after deletion
        val expectedSize = 9
        val resultSize = this.monthInformation.summary.size
        assertEquals(expectedSize, resultSize)
    }
}
