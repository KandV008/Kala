package com.example.kala.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering a title component.
 *
 * @param configuration The configuration data for the title.
 */
@Composable
fun Title(configuration: TitleConfiguration) {
    Box(
        modifier = Modifier
            .height(dimens.height1)
            .width(dimens.width7)
            .border(dimens.border, Color.Black)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = configuration.getDisplayName()),
            fontSize = dimens.fontSize5,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Composable function for previewing the Title component.
 * This preview function is used for testing and visualizing the Title component.
 */
@Preview
@Composable
fun TitlePreview() {
    Column {
        Title(TitleConfiguration.RECOVERY_PASS)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Title(TitleConfiguration.CHANGE_PASS)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Title(TitleConfiguration.HELP)
    }
}
