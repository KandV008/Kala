package com.example.kala.ui.screens.utilities

import android.annotation.SuppressLint
import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat

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


}