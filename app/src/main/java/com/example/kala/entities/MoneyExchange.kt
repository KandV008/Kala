package com.example.kala.entities

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class MoneyExchange(
    var value: Double,
    val type: MoneyExchangeType,
    val scope: MoneyExchangeScope,
    val description: String? = null,
    val date: LocalDateTime = LocalDateTime.now()
) {
    fun getFormattedDate(): String {
        val formatter = DateTimeFormatter.ofPattern("dd MMM yy", Locale.ENGLISH)
        return date.format(formatter)
    }

    fun getFormattedValue(): String {
        return String.format("%.2f", value)
    }

    constructor(value: Double, type: String, scope: String, description: String? = null) : this(
        value,
        MoneyExchangeType.valueOf(type),
        MoneyExchangeScope.valueOf(scope),
        description
    )
}
