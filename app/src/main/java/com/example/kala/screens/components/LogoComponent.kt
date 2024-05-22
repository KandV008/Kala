package com.example.kala.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.configuration.LogoConfiguration
import com.example.kala.configuration.NAME_APPLICATION
import com.example.kala.ui.theme.dimens
import com.example.kala.ui.theme.fontFamily

/**
 * Composable function for rendering the logo component.
 *
 * @param configuration The configuration data for the logo.
 */
@Composable
fun Logo(configuration: LogoConfiguration) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .size(configuration.getSize())
            .border(dimens.border, Color.Black)
    ) {
        Text(
            text = NAME_APPLICATION,
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            fontSize = dimens.fontSize6,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily
        )
    }
}

/**
 * Composable function for previewing the Logo component.
 * This preview function is used for testing and visualizing the Logo component.
 */
@Preview
@Composable
fun LogoPreview() {
    Column {
        Logo(LogoConfiguration.SMALL)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Logo(LogoConfiguration.LARGE)
    }
}
