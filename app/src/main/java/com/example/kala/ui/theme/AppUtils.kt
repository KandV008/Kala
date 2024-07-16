package com.example.kala.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

/**
 * A composable function that provides application dimensions to the content.
 *
 * @param appDimens The dimensions to be used throughout the application.
 * @param content The composable content that will use the provided dimensions.
 */
@Composable
fun AppUtils(
    appDimens: Dimens,
    content: @Composable () -> Unit
) {
    val newAppDimens = remember {
        appDimens
    }

    CompositionLocalProvider(LocalAppDimens provides newAppDimens) {
        content()
    }
}

/**
 * A CompositionLocal for providing the application dimensions.
 */
val LocalAppDimens = compositionLocalOf {
    CompactDimens
}