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

object Utilities {

    /**
     * Returns the formatted value string with two decimal places.
     *
     * @return The formatted value string.
     */
    @SuppressLint("DefaultLocale")
    fun formatMoneyValue(value: Double): String{
        return String.format("%.2f", value) + "€"
    }

    @Composable
    fun SetLocale(action: String) {
        val context = LocalContext.current

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(action)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(action))
        }
    }

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
     * @return The formatted date string.
     */
    fun getFormattedDate(date: LocalDateTime): String{
        val formatter = DateTimeFormatter.ofPattern("dd MMM yy", Locale.getDefault())
        return date.format(formatter)
    }
}