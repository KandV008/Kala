package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val UNREGISTERED_USER = "Unregistered User"
const val REGISTERED_USER = "Registered User"
const val HELP_SCREEN = "Help Screen"
const val LANGUAGE_SCREEN = "Language Screen"

val displayLeftHeader: Map<String, Pair<String, Float>> = mapOf(
    UNREGISTERED_USER to Pair(HELP, 1F),
    REGISTERED_USER to Pair(HELP, 1F),
    HELP_SCREEN to Pair(HELP, 0F),
    LANGUAGE_SCREEN to Pair(HELP, 1F),
)

val displayCenterHeader: Map<String, Float> = mapOf(
    UNREGISTERED_USER to 0F,
    REGISTERED_USER to 1F,
    HELP_SCREEN to 1F,
    LANGUAGE_SCREEN to 1F,
)

val displayRightHeader: Map<String, Pair<String, Float>> = mapOf(
    UNREGISTERED_USER to Pair(LANGUAGE, 1F),
    REGISTERED_USER to Pair(OPTIONS, 1F),
    HELP_SCREEN to Pair(LANGUAGE, 1F),
    LANGUAGE_SCREEN to Pair(OPTIONS, 0F),
)

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Header(configuration: String = "") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RectangleShape)
            .background(Color.White)
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NavigationButton(
                configuration = displayLeftHeader
                    .getOrDefault(configuration, Pair("", 0F)).first,
                alpha = displayLeftHeader
                    .getOrDefault(configuration, Pair("", 0F)).second,
            )
            Text(
                text = "Kala",
                color = Color.Black,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .alpha(displayCenterHeader.getOrDefault(configuration, 1F))
            )
            NavigationButton(
                configuration = displayRightHeader
                    .getOrDefault(configuration, Pair("", 0F)).first,
                alpha = displayRightHeader
                    .getOrDefault(configuration, Pair("", 0F)).second,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun HeaderPreview() {
    Column {
        Header(UNREGISTERED_USER)
        Spacer(modifier = Modifier.padding(5.dp))
        Header(REGISTERED_USER)
        Spacer(modifier = Modifier.padding(5.dp))
        Header(HELP_SCREEN)
        Spacer(modifier = Modifier.padding(5.dp))
        Header(LANGUAGE_SCREEN)
    }
}