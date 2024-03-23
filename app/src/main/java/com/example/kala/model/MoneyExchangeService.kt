package com.example.kala.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kala.entities.MoneyExchange
import com.example.kala.storage.MoneyExchangeStorage

/**
 * Service class for managing money exchanges.
 */
object MoneyExchangeService {

    @RequiresApi(Build.VERSION_CODES.O)
    val moneyExchangeStorage = MoneyExchangeStorage()

    /**
     * Adds a new money exchange.
     *
     * @param moneyExchange The money exchange to add.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun addMoneyExchange(moneyExchange: MoneyExchange): Boolean{
        // TODO: Service validation

        moneyExchangeStorage.saveMoneyExchange(moneyExchange)
        return true
    }

    /**
     * Retrieves all money exchanges.
     *
     * @return A list of all money exchanges.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllMoneyExchanges(): List<MoneyExchange> {
        return moneyExchangeStorage.getAllMoneyExchange().reversed()
    }

    /**
     * Retrieves a specific money exchange.
     *
     * @param monthAssociated The month associated with the money exchange.
     * @param exchange The index of the money exchange.
     * @return The requested money exchange.
     * @throws IllegalAccessError if the requested money exchange is not found.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange {
        val moneyExchange = moneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)

        if (moneyExchange != null) {
            return moneyExchange.copy()
        }

        // TODO: Handle error
        throw IllegalAccessError()
    }
}
