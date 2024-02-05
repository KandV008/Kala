package com.example.kala.entities

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

data class MoneyExchange(
    var value: Int,
    val type: MoneyExchangeType,
    val scope: MoneyExchangeScope,
    val description: String? = null,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    val date: LocalDateTime = LocalDateTime.now()
}