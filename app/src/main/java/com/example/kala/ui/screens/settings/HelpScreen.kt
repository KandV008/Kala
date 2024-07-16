package com.example.kala.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.HELP_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering the Help screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HelpScreen(
    navController: NavController? = null,
    triggerScreen: String,
) {
    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.HELP_SCREEN,
        triggerScreen = HELP_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.ONLY_BACK,
        onAdviceTriggered = { }
    ) {
        Title(configuration = TitleConfiguration.HELP)
        Spacer(modifier = Modifier.padding(dimens.space3))
        HelpScreenBody(HelpService.getTextAdviceScreen(triggerScreen))
        Spacer(modifier = Modifier.padding(dimens.space3))
    }
}

/**
 * Composable function for rendering the body of the Help screen.
 *
 * @param advices The list of advices to display in the Help screen.
 */
@Composable
fun HelpScreenBody(advices: List<Int>) {
    LazyColumn(
        modifier = Modifier
            .width(dimens.width7)
            .height(dimens.height10)
            .background(Color.White)
            .border(dimens.border, Color.Black)
            .padding(dimens.padding5),
    ) {
        items(advices) { advice ->
            Text(
                text = stringResource(id = advice),
                color = Color.Black,
                fontSize = dimens.fontSize0
            )
            Spacer(modifier = Modifier.padding(dimens.space1))
        }
    }
}

/**
 * Composable function for previewing the Help screen.
 * This preview function is used for testing and visualizing the Help screen.
 */
@Preview(showBackground = true)
@Composable
fun HelpScreenPreview() {
    HelpScreen(triggerScreen = HOME_SCREEN_ROUTE)
}
