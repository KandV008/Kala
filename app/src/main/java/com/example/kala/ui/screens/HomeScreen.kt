package com.example.kala.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.ui.components.ChartConfiguration
import com.example.kala.ui.components.buttons.LargeButton
import com.example.kala.ui.components.buttons.LargeButtonConfiguration
import com.example.kala.ui.components.buttons.MediumButton
import com.example.kala.ui.components.buttons.MediumButtonConfiguration
import com.example.kala.ui.components.charts.BarChartInfo
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.RECORD_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.REPORT_SCREEN_ROUTE
import com.example.kala.ui.theme.dimens
import java.time.LocalDateTime

/**
 * Composable function for rendering the Home screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController? = null
) {
    val today = LocalDateTime.now()
    val currentMonth = "${today.month}${today.year}"

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.REGISTERED_USER,
        triggerScreen = HOME_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.EMPTY,
        onAdviceTriggered = { }
    ) {
        BarChartInfo(configuration = ChartConfiguration.HOME_PAGE, currentMonth)
        Spacer(modifier = Modifier.padding(dimens.padding2))
        HomeScreenBody(navController, currentMonth)
    }

}

/**
 * Composable function for rendering the body of the Home screen.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param currentMont The current month to be passed to the report screen.
 */
@Composable
fun HomeScreenBody(
    navController: NavController? = null,
    currentMont: String
) {
    var centerButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onCenterTriggered = {
        centerButtonTriggered = true
    }
    if (centerButtonTriggered) {
        centerButtonTriggered = false
        navController?.navigate(route = ADD_EXCHANGE_SCREEN_ROUTE)
    }

    var leftButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onLeftTriggered = {
        leftButtonTriggered = true
    }
    if (leftButtonTriggered) {
        leftButtonTriggered = false
        navController?.navigate(route = "$REPORT_SCREEN_ROUTE/$currentMont")
    }

    var rightButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onRightTriggered = {
        rightButtonTriggered = true
    }
    if (rightButtonTriggered) {
        rightButtonTriggered = false
        navController?.navigate(route = RECORD_SCREEN_ROUTE)
    }

    LargeButton(
        configuration = LargeButtonConfiguration.ADD_EXCHANGE,
        onAdviceTriggered = onCenterTriggered
    )
    Spacer(modifier = Modifier.padding(dimens.space0))
    Row {
        MediumButton(configuration = MediumButtonConfiguration.SEE_REPORT, onLeftTriggered)
        Spacer(modifier = Modifier.padding(dimens.space1))
        MediumButton(configuration = MediumButtonConfiguration.SEE_RECORD, onRightTriggered)
    }
}

/**
 * Composable function for previewing the Home screen.
 * This preview function is used for testing and visualizing the Home screen.
 */
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

