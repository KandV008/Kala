package com.example.kala.ui.screens.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
import com.example.kala.ui.components.FooterConfiguration
import com.example.kala.ui.components.buttons.NavigationButton
import com.example.kala.ui.components.buttons.NavigationButtonConfiguration
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering the footer component.
 *
 * @param configuration The configuration data for the footer.
 * @param navController The navigation controller to handle navigation actions.
 * @param onAdviceTriggered The callback for handling advice triggered event.
 */
@Composable
fun Footer(
    configuration: FooterConfiguration,
    navController: NavController? = null,
    onAdviceTriggered: () -> Unit = {},
) {

    var leftTriggered by remember { mutableStateOf(false) }
    var centerTriggered by remember { mutableStateOf(false) }
    var rightTriggered by remember { mutableStateOf(false) }

    val onLeftTriggered = {
        leftTriggered = true
    }
    val onCenterTriggered = {
        centerTriggered = true
    }
    val onRightTriggered = {
        rightTriggered = true
    }

    if (leftTriggered){
        leftTriggered = false
        navController?.popBackStack()
    }

    if (centerTriggered){
        centerTriggered = false
        navController?.navigate(route = HOME_SCREEN_ROUTE)
    }

    if (rightTriggered){
        rightTriggered = false
        onAdviceTriggered()
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
                configuration = NavigationButtonConfiguration.BACK,
                alpha = configuration.left(),
                onAdviceTriggered = onLeftTriggered,
                )
            NavigationButton(
                configuration = NavigationButtonConfiguration.HOME,
                alpha = configuration.center(),
                onAdviceTriggered = onCenterTriggered,
                )
            NavigationButton(
                configuration = NavigationButtonConfiguration.NEXT,
                alpha = configuration.right(),
                onAdviceTriggered = onRightTriggered,
            )
        }
    }
}

/**
 * Composable function for previewing the Footer component.
 * This preview function is used for testing and visualizing the Footer component.
 */
@Preview
@Composable
fun FooterPreview() {
    Column {
        Footer(FooterConfiguration.EMPTY)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Footer(FooterConfiguration.ONLY_BACK)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Footer(FooterConfiguration.ONLY_NEXT)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Footer(FooterConfiguration.BACK_AND_NEXT)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Footer(FooterConfiguration.BACK_AND_HOME)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Footer(FooterConfiguration.NEXT_AND_HOME)
        Spacer(modifier = Modifier.padding(dimens.space0))
        Footer(FooterConfiguration.ALL)
    }
}
