package com.example.kala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.navigation.AppNavigation
import com.example.kala.ui.theme.KalaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalaTheme {
                // A surface container using the 'background' color from the theme
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