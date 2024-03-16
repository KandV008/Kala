package com.example.kala.entities

import java.time.LocalDateTime

data class MoneyExchange(
    var value: Double,
    val type: MoneyExchangeType,
    val scope: MoneyExchangeScope,
    val description: String? = null,
    val date: LocalDateTime = LocalDateTime.now()
) {
    constructor(value: Double, type: String, scope: String, description: String? = null) : this(
        value,
        MoneyExchangeType.valueOf(type),
        MoneyExchangeScope.valueOf(scope),
        description
    )
}
