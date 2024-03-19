package com.example.kala.entities

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kala.R

enum class MoneyExchangeScope {
    FOOD, LEISURE, USEFUL, MEDICINE, OTHER;

    companion object {
        @RequiresApi(Build.VERSION_CODES.N)
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

