package com.example.kala.entities

import com.example.kala.R

/**
 * Enum representing the scope/category of a money exchange.
 */
enum class MoneyExchangeScope {
    FOOD, LEISURE, USEFUL, MEDICINE, OTHER;

    companion object {
        /**
         * Gets the SVG file associated with the given MoneyExchangeScope enum.
         *
         * @param enum The MoneyExchangeScope enum.
         * @return The resource ID of the SVG file.
         */
        fun getSVGFile(enum: MoneyExchangeScope): Int {
            val svgMap: Map<MoneyExchangeScope, Int> = mapOf(
                FOOD to R.drawable.ic_food_scope,
                LEISURE to R.drawable.ic_leisure_scope,
                MEDICINE to R.drawable.ic_medicine_scope,
                USEFUL to R.drawable.ic_utilities_scope,
                OTHER to R.drawable.ic_other_scope,
            )
            return svgMap.getOrDefault(enum, R.drawable.ic_question)
        }
    }
}
