package com.example.kala.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kala.entities.MoneyExchange
import com.example.kala.storage.MoneyExchangeStorage

class MoneyExchangeService {

    @RequiresApi(Build.VERSION_CODES.O)
    fun addMoneyExchange(moneyExchange: MoneyExchange){
        // TODO Service validation

        MoneyExchangeStorage.saveMoneyExchange(moneyExchange)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllMoneyExchanges(): List<MoneyExchange> {
        return MoneyExchangeStorage.getAllMoneyExchange();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMoneyExchange(monthAssociated: String, exchange: Int): MoneyExchange {
        val moneyExchange = MoneyExchangeStorage.getMoneyExchange(monthAssociated, exchange)

        if (moneyExchange != null) {
            return moneyExchange.copy()
        }

        // TODO Handler error
        throw IllegalAccessError()
    }
}