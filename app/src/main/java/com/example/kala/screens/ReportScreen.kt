package com.example.kala.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.configuration.ABOUT_MONTH_SCREEN_ROUTE
import com.example.kala.configuration.ChartConfiguration
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.MediumButtonConfiguration
import com.example.kala.configuration.REPORT_SCREEN_ROUTE
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.model.MoneyExchangeService
import com.example.kala.screens.components.BarChartInfo
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.buttons.MediumButton
import com.example.kala.ui.theme.BoneWhite
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
    val monthInformation = MoneyExchangeService.getMonthInformation(currentMonth)

    var leftButtonTriggered by remember { mutableStateOf(false) }
    val onLeftTriggered = { leftButtonTriggered = true }
    var rightButtonTriggered by remember { mutableStateOf(false) }
    val onRightTriggered = { rightButtonTriggered = true }

    if (rightButtonTriggered){
        rightButtonTriggered = false
        val nextMonth = MoneyExchangeService.getNextMonth(monthInformation)
        navController?.navigate(route = "$REPORT_SCREEN_ROUTE/$nextMonth")
    }

    if (leftButtonTriggered){
        leftButtonTriggered = false
        val prevMonth = MoneyExchangeService.getPrevMonth(monthInformation)
        navController?.navigate(route = "$REPORT_SCREEN_ROUTE/$prevMonth")
    }

    val goBackTriggered = {
        navController?.navigate(route = HOME_SCREEN_ROUTE)
    }

    Scaffold(
        topBar = {
            Header(
                configuration = HeaderConfiguration.REGISTERED_USER,
                navController = navController,
                triggerScreen = REPORT_SCREEN_ROUTE,
            )
        },
        bottomBar = {
            Footer(
                configuration = FooterConfiguration.BACK_AND_HOME,
                navController = navController,
                changeGoBackButton = true,
                goBackTriggered = goBackTriggered,
            )
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(dimens.space4))
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
            Spacer(modifier = Modifier.padding(dimens.space4))
        }
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

