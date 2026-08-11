package com.example.kala.ui.screens.monthInformation

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
import com.example.kala.model.MonthInformationService
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.ui.components.ChartConfiguration
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.components.buttons.MediumButton
import com.example.kala.ui.components.buttons.MediumButtonConfiguration
import com.example.kala.ui.components.charts.BarChartInfo
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.ABOUT_MONTH_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.REPORT_SCREEN_ROUTE
import com.example.kala.ui.theme.dimens
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

/**
 * Composable function for rendering the Report screen.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param currentMonth The current month identifier.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReportScreen(
    navController: NavController? = null,
    currentMonth: String,
) {
    var monthId by remember { mutableStateOf(currentMonth) }
    val monthInformation = MonthInformationService.getMonthInformation(monthId)

    var leftButtonTriggered by remember { mutableStateOf(false) }
    val onLeftTriggered = { leftButtonTriggered = true }
    var rightButtonTriggered by remember { mutableStateOf(false) }
    val onRightTriggered = { rightButtonTriggered = true }

    if (rightButtonTriggered) {
        rightButtonTriggered = false
        monthId = MonthInformationService.getNextMonth(monthInformation)
    }

    if (leftButtonTriggered) {
        leftButtonTriggered = false
        monthId = MonthInformationService.getPrevMonth(monthInformation)
    }

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.REGISTERED_USER,
        triggerScreen = REPORT_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_HOME,
        onAdviceTriggered = { }
    ) {
        Title(configuration = TitleConfiguration.REPORT)
        Spacer(modifier = Modifier.padding(dimens.space1))
        BarChartInfo(
            configuration = ChartConfiguration.REPORT_PAGE,
            month = monthId,
            onLeftTriggered = onLeftTriggered,
            onRightTriggered = onRightTriggered,
        )
        Spacer(modifier = Modifier.padding(dimens.space1))
        ReportScreenBody(navController, monthId)
    }

}

/**
 * Composable function for rendering the body of the Report screen.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param currentMonth The current month identifier.
 */
@Composable
fun ReportScreenBody(
    navController: NavController? = null,
    currentMonth: String
) {
    var leftButtonTriggered by remember { mutableStateOf(false) }
    val onLeftTriggered = { leftButtonTriggered = true }
    var rightButtonTriggered by remember { mutableStateOf(false) }
    val onRightTriggered = { rightButtonTriggered = true }

    if (leftButtonTriggered) {
        leftButtonTriggered = false
        navController?.navigate(route = "$ABOUT_MONTH_SCREEN_ROUTE/$currentMonth/${MoneyExchangeType.INCOME}")
    }

    if (rightButtonTriggered) {
        rightButtonTriggered = false
        navController?.navigate(route = "$ABOUT_MONTH_SCREEN_ROUTE/$currentMonth/${MoneyExchangeType.EXPENSE}")
    }

    Row {
        MediumButton(configuration = MediumButtonConfiguration.INCOME, onLeftTriggered)
        Spacer(modifier = Modifier.padding(dimens.space1))
        MediumButton(configuration = MediumButtonConfiguration.EXPENSE, onRightTriggered)
    }
}

/**
 * Preview for the Report screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ReportScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        ReportScreen(currentMonth = "example")
    }
}

/**
 * Preview for the Report screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ReportScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        ReportScreen(currentMonth = "example")
    }
}

/**
 * Preview for the Report screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ReportScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        ReportScreen(currentMonth = "example")
    }
}

/**
 * Preview for the Report screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ReportScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        ReportScreen(currentMonth = "example")
    }
}

/**
 * Preview for the Report screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ReportScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        ReportScreen(currentMonth = "example")
    }
}
