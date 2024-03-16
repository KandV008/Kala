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
}