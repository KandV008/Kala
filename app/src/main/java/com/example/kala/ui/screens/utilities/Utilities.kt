package com.example.kala.ui.screens.utilities

import android.annotation.SuppressLint
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import com.example.kala.R
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Utility object containing various helper functions used throughout the application.
 */
object Utilities {

    /**
     * Returns the formatted value string with two decimal places followed by the Euro symbol.
     *
     * @param value The monetary value to be formatted.
     * @return The formatted value string.
     */
    @SuppressLint("DefaultLocale")
    fun formatMoneyValue(value: Double): String {
        return String.format("%.2f", value) + "€"
    }

    /**
     * Composable function to set the application locale.
     *
     * @param action The language tag to set as the current locale.
     */
    @Composable
    fun SetLocale(action: String) {
        val context = LocalContext.current

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(action)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(action))
        }
    }

    /**
     * Retrieves the current locale of the application.
     *
     * @param context The context from which to get the locale.
     * @return The current locale as a language tag string.
     */
    fun getLocale(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                ?.applicationLocales
                ?.toLanguageTags()
                ?: ""
        } else {
            AppCompatDelegate.getApplicationLocales().toLanguageTags()
        }
    }

    /**
     * Returns the string resource ID for a given Month enum.
     *
     * @param month The month for which to get the string resource ID.
     * @return The string resource ID corresponding to the month, or -1 if the month is null.
     */
    fun getMonthString(month: Month?): Int {
        return when (month) {
            Month.JANUARY -> R.string.january
            Month.FEBRUARY -> R.string.february
            Month.MARCH -> R.string.march
            Month.APRIL -> R.string.april
            Month.MAY -> R.string.may
            Month.JUNE -> R.string.june
            Month.JULY -> R.string.july
            Month.AUGUST -> R.string.august
            Month.SEPTEMBER -> R.string.september
            Month.OCTOBER -> R.string.october
            Month.NOVEMBER -> R.string.november
            Month.DECEMBER -> R.string.december
            else -> -1
        }
    }

    /**
     * Returns the date string in the format "dd MMM yy".
     *
     * @param date The date to be formatted.
     * @return The formatted date string.
     */
    fun getFormattedDate(date: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("dd MMM yy", Locale.getDefault())
        return date.format(formatter)
    }
}