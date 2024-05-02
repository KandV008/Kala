package com.example.kala.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

@Composable
fun AppUtils(
    appDimens: Dimens,
    content: @Composable () -> Unit
){
    val newAppDimens = remember {
        appDimens
    }
    
    CompositionLocalProvider(LocalAppDimens provides newAppDimens) {
        content()
    }
}

val LocalAppDimens = compositionLocalOf {
    CompactDimens
}