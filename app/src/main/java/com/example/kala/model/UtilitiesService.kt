package com.example.kala.model

import android.annotation.SuppressLint

object UtilitiesService {

    /**
     * Returns the formatted value string with two decimal places.
     *
     * @return The formatted value string.
     */
    @SuppressLint("DefaultLocale")
    fun formatMoneyValue(value: Double): String{
        return String.format("%.2f", value) + "€"
    }

}