package com.example.kala.components

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
import androidx.navigation.NavController
import com.example.kala.navigation.HELP_SCREEN_ROUTE
import com.example.kala.navigation.LANGUAGE_SCREEN_ROUTE
import com.example.kala.navigation.OPTION_SCREEN_ROUTE
import com.example.kala.components.buttons.NavigationButton
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering the header component.
 *
 * @param configuration The configuration data for the header.
 * @param navController The navigation controller to handle navigation actions.
 */
@Composable
fun Header(
    configuration: HeaderConfiguration,
    navController: NavController? = null,
    triggerScreen: String = "",
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
        navController?.navigate(route = "$HELP_SCREEN_ROUTE/$triggerScreen")
    }

    if (rightTriggered){
        rightTriggered = false
        navController?.navigate(route = rightRoute)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(dimens.border, Color.Black, RectangleShape)
            .background(Color.White)
            .padding(dimens.padding6, dimens.padding3)
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
                text = NAME_APPLICATION,
                color = Color.Black,
                fontSize = dimens.fontSize4,
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
@Preview
@Composable
fun HeaderPreview() {
    Column {
        Header(HeaderConfiguration.UNREGISTERED_USER)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Header(HeaderConfiguration.REGISTERED_USER)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Header(HeaderConfiguration.HELP_SCREEN)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Header(HeaderConfiguration.LANGUAGE_SCREEN)
    }
}
