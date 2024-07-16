package com.example.kala.model.entities

import androidx.compose.ui.graphics.Color
import com.example.kala.R
import com.example.kala.ui.theme.Blue0
import com.example.kala.ui.theme.Green0
import com.example.kala.ui.theme.Pink0
import com.example.kala.ui.theme.Purple0
import com.example.kala.ui.theme.Yellow0

val FOOD_SCOPE_LABEL = R.string.food_scope_label
val LEISURE_SCOPE_LABEL = R.string.leisure_scope_label
val USEFUL_SCOPE_LABEL = R.string.useful_scope_label
val MEDICINE_SCOPE_LABEL = R.string.medicine_scope_label
val OTHER_SCOPE_LABEL = R.string.other_scope_label

/**
 * Enum representing the scope/category of a money exchange.
 */
enum class MoneyExchangeScope(
    private val color: Color,
    private val label: Int,
) {
    FOOD(Pink0, FOOD_SCOPE_LABEL),
    LEISURE(Purple0, LEISURE_SCOPE_LABEL),
    USEFUL(Blue0, USEFUL_SCOPE_LABEL),
    MEDICINE(Green0, MEDICINE_SCOPE_LABEL),
    OTHER(Yellow0, OTHER_SCOPE_LABEL);

    fun getColor(): Color {
        return color
    }

    fun getLabel(): Int {
        return label
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
