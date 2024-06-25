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

/**
 * Composable function for rendering the Report screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReportScreen(
    navController: NavController? = null,
    currentMonth: String,
){
    val monthInformation = MonthInformationService.getMonthInformation(currentMonth)

    var leftButtonTriggered by remember { mutableStateOf(false) }
    val onLeftTriggered = { leftButtonTriggered = true }
    var rightButtonTriggered by remember { mutableStateOf(false) }
    val onRightTriggered = { rightButtonTriggered = true }

    if (rightButtonTriggered){
        rightButtonTriggered = false
        val nextMonth = MonthInformationService.getNextMonth(monthInformation)
        navController?.navigate(route = "$REPORT_SCREEN_ROUTE/$nextMonth")
    }

    if (leftButtonTriggered){
        leftButtonTriggered = false
        val prevMonth = MonthInformationService.getPrevMonth(monthInformation)
        navController?.navigate(route = "$REPORT_SCREEN_ROUTE/$prevMonth")
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
            month = currentMonth,
            onLeftTriggered = onLeftTriggered,
            onRightTriggered = onRightTriggered,
        )
        Spacer(modifier = Modifier.padding(dimens.space1))
        ReportScreenBody(navController, currentMonth)
    }

}

/**
 * Composable function for rendering the body of the Report screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@Composable
fun ReportScreenBody(
    navController: NavController? = null,
    currentMonth: String
){
    var leftButtonTriggered by remember { mutableStateOf(false) }
    val onLeftTriggered = { leftButtonTriggered = true }
    var rightButtonTriggered by remember { mutableStateOf(false) }
    val onRightTriggered = { rightButtonTriggered = true }

    if (leftButtonTriggered){
        leftButtonTriggered = false
        navController?.navigate(route = "$ABOUT_MONTH_SCREEN_ROUTE/$currentMonth/${MoneyExchangeType.INCOME}")
    }

    if (rightButtonTriggered){
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
 * Composable function for previewing the Report screen.
 * This preview function is used for testing and visualizing the Report screen.
 */
@Preview(showBackground = true)
@Composable
fun ReportScreenPreview(){
    ReportScreen(currentMonth = "example")
}

