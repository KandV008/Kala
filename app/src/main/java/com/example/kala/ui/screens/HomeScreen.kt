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
import com.example.kala.ui.screens.utilities.LARGE_PHONE_HEIGHT_DP
import com.example.kala.ui.screens.utilities.LARGE_PHONE_NAME
import com.example.kala.ui.screens.utilities.LARGE_PHONE_WIDTH_DP
import com.example.kala.ui.screens.utilities.LARGE_TABLET_HEIGHT_DP
import com.example.kala.ui.screens.utilities.LARGE_TABLET_NAME
import com.example.kala.ui.screens.utilities.LARGE_TABLET_WIDTH_DP
import com.example.kala.ui.screens.utilities.MEDIUM_PHONE_HEIGHT_DP
import com.example.kala.ui.screens.utilities.MEDIUM_PHONE_NAME
import com.example.kala.ui.screens.utilities.MEDIUM_PHONE_WIDTH_DP
import com.example.kala.ui.screens.utilities.SMALL_PHONE_HEIGHT_DP
import com.example.kala.ui.screens.utilities.SMALL_PHONE_NAME
import com.example.kala.ui.screens.utilities.SMALL_PHONE_WIDTH_DP
import com.example.kala.ui.screens.utilities.TABLET_HEIGHT_DP
import com.example.kala.ui.screens.utilities.TABLET_NAME
import com.example.kala.ui.screens.utilities.TABLET_WIDTH_DP
import com.example.kala.ui.theme.AppUtils
import com.example.kala.ui.theme.LargePhone
import com.example.kala.ui.theme.LargeTablet
import com.example.kala.ui.theme.MediumPhone
import com.example.kala.ui.theme.SmallPhone
import com.example.kala.ui.theme.Tablet
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
 * Composable function for previewing the Home screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HomeScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        HomeScreen()
    }
}

/**
 * Composable function for previewing the Home screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HomeScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        HomeScreen()
    }
}

/**
 * Composable function for previewing the Home screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HomeScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        HomeScreen()
    }
}

/**
 * Composable function for previewing the Home screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HomeScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        HomeScreen()
    }
}

/**
 * Composable function for previewing the Home screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HomeScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        HomeScreen()
    }
}