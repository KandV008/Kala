package com.example.kala.model

import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import org.junit.Before
import org.junit.Test

class MoneyExchangeServiceTest {

    private lateinit var moneyExchange: MoneyExchange
    private lateinit var service: MoneyExchangeService


    @Before
    fun onBefore(){
        val value = 30.0
        val type: MoneyExchangeType = MoneyExchangeType.EXPENSE
        val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
        val description = "Compra semanal"
        moneyExchange = MoneyExchange(value, type, scope, description)
        service = MoneyExchangeService()
    }

    @Test
    fun addMoneyExchange() {
        service.addMoneyExchange(moneyExchange)
        assert(true)
    }
}