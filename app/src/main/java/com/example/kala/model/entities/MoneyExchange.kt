package com.example.kala.model.entities

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Represents a money exchange object.
 *
 * @property value The value of the money exchange.
 * @property type The type of the money exchange.
 * @property scope The scope/category of the money exchange.
 * @property description Additional description of the money exchange.
 * @property id The unique identifier of the money exchange.
 * @property monthAssociated The month associated with the money exchange.
 * @property date The date and time of the money exchange.
 */
data class MoneyExchange(
    var value: Double,
    var type: MoneyExchangeType,
    var scope: MoneyExchangeScope,
    var description: String? = null,
) {
    var id: Int = -1
    var monthAssociated: String = ""
    var date: LocalDateTime = LocalDateTime.now()

    /**
     * Secondary constructor that takes string values for type and scope and converts them to enum values.
     */
    constructor(value: Double, type: String, scope: String, description: String? = null) : this(
        value,
        MoneyExchangeType.valueOf(type),
        MoneyExchangeScope.valueOf(scope),
        description
    )

    /**
     * Returns the formatted date string in the format "dd MMM yy".
     *
     * @return The formatted date string.
     */
    fun getFormattedDate(): String {
        val formatter = DateTimeFormatter.ofPattern("dd MMM yy", Locale.getDefault())
        return date.format(formatter)
    }


    /**
     * Overrides the equals function to compare MoneyExchange objects based on their dates.
     *
     * @return True if the objects have the same date, false otherwise.
     */
    override fun equals(other: Any?): Boolean {
        val otherMoneyExchange: MoneyExchange = other as MoneyExchange
        return date == otherMoneyExchange.date
    }

    /**
     * Overrides the hashCode function to generate a hash code based on the object's properties.
     *
     * @return The hash code.
     */
    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + scope.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + id
        result = 31 * result + monthAssociated.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }

    /**
     * Overrides the toString function to generate a string based on the object's properties.
     *
     * @return The string.
     */
    override fun toString(): String {
        return "Money Exchange $id from $monthAssociated: \n" +
                "\t value = $value; type = $type; scope = $scope; description = $description; date = $date"
    }
}
