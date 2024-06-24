package com.example.kala

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.ui.components.buttons.NavigationButtonConfiguration
import com.example.kala.ui.components.buttons.getLanguageIconViaContext
import com.example.kala.ui.screens.AppNavigation
import com.example.kala.ui.theme.KalaTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val languageIcon = getLanguageIconViaContext(this)
        NavigationButtonConfiguration.LANGUAGE.updateIcon(languageIcon)

        setContent {
            KalaTheme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KalaTheme {
        AppNavigation()
    }
}