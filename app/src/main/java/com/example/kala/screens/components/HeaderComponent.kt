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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.example.kala.configuration.HELP_SCREEN_ROUTE
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.LANGUAGE_SCREEN_ROUTE
import com.example.kala.configuration.OPTION_SCREEN_ROUTE
import com.example.kala.screens.components.buttons.NavigationButton

/**
 * Composable function for rendering the header component.
 *
 * @param configuration The configuration data for the header.
 * @param navController The navigation controller to handle navigation actions.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Header(
    configuration: HeaderConfiguration,
    navController: NavController? = null,
    ) {
    val rightRoute =
        if (HeaderConfiguration.REGISTERED_USER == configuration)
            OPTION_SCREEN_ROUTE
        else LANGUAGE_SCREEN_ROUTE
    var leftTriggered by remember { mutableStateOf(false) }
    var rightTriggered by remember { mutableStateOf(false) }

    val onLeftTriggered = {
        leftTriggered = true
    }
    val onRightTriggered = {
        rightTriggered = true
    }

    if (leftTriggered){
        leftTriggered = false
        navController?.navigate(route = HELP_SCREEN_ROUTE)
    }

    if (rightTriggered){
        rightTriggered = false
        navController?.navigate(route = rightRoute)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RectangleShape)
            .background(Color.White)
            .padding(30.dp, 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NavigationButton(
                configuration = configuration.left().first,
                alpha = configuration.left().second,
                onAdviceTriggered = onLeftTriggered,
            )
            Text(
                text = "Kala",
                color = Color.Black,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(configuration.center())
            )
            NavigationButton(
                configuration = configuration.right().first,
                alpha = configuration.right().second,
                onAdviceTriggered = onRightTriggered,
            )
        }
    }
}

/**
 * Composable function for previewing the Header component.
 * This preview function is used for testing and visualizing the Header component.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun HeaderPreview() {
    Column {
        Header(HeaderConfiguration.UNREGISTERED_USER)
        Spacer(modifier = Modifier.padding(5.dp))
        Header(HeaderConfiguration.REGISTERED_USER)
        Spacer(modifier = Modifier.padding(5.dp))
        Header(HeaderConfiguration.HELP_SCREEN)
        Spacer(modifier = Modifier.padding(5.dp))
        Header(HeaderConfiguration.LANGUAGE_SCREEN)
    }
}
