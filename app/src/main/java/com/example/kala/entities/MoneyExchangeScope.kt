package com.example.kala.entities

import androidx.compose.ui.graphics.Color
import com.example.kala.R

/**
 * Enum representing the scope/category of a money exchange.
 */
enum class MoneyExchangeScope(
    private val color: Color,
) {
    FOOD(Color.Cyan),
    LEISURE(Color.Magenta),
    USEFUL(Color.Yellow),
    MEDICINE(Color.Red),
    OTHER(Color.Green);

    fun getColor(): Color{
        return color
    }

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
