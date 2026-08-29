package com.example.kala.ui.screens.monthInformation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.model.MoneyExchangeService
import com.example.kala.model.MonthInformationService
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.model.entities.MonthInformation
import com.example.kala.ui.components.SVG_DESCRIPTION
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.components.charts.PieChartInfo
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.ABOUT_MONTH_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.Utilities
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
 * Composable function for rendering the About month screen.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param month The month identifier.
 * @param type The type of money exchange (income or expense).
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutMonthScreen(
    navController: NavController? = null,
    month: String,
    type: String,
) {
    var monthId by remember { mutableStateOf(month) }
    val currentMonth = MonthInformationService.getMonthInformation(monthId)
    val currentType = MoneyExchangeType.valueOf(type)

    val titleConfiguration =
        if (currentType == MoneyExchangeType.EXPENSE)
            TitleConfiguration.EXPENSE
        else
            TitleConfiguration.INCOME

    var leftButtonTriggered by remember { mutableStateOf(false) }
    val onLeftTriggered = { leftButtonTriggered = true }
    var rightButtonTriggered by remember { mutableStateOf(false) }
    val onRightTriggered = { rightButtonTriggered = true }

    if (rightButtonTriggered) {
        rightButtonTriggered = false
        monthId = MonthInformationService.getNextMonth(currentMonth)
    }

    if (leftButtonTriggered) {
        leftButtonTriggered = false
        monthId = MonthInformationService.getPrevMonth(currentMonth)
    }

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.REGISTERED_USER,
        triggerScreen = ABOUT_MONTH_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_HOME,
        onAdviceTriggered = { }
    ) {
        Title(configuration = titleConfiguration)
        Spacer(modifier = Modifier.padding(dimens.space1))
        PieChartInfo(
            month = monthId,
            type = type,
            onLeftTriggered = onLeftTriggered,
            onRightTriggered = onRightTriggered
        )
        Spacer(modifier = Modifier.padding(dimens.space0))
        Box(
            modifier = Modifier
                .width(dimens.width8)
                .height(dimens.heightSummaryScope)
                .clip(RoundedCornerShape(dimens.rounded))
                .background(Color.White)
                .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
            contentAlignment = Alignment.Center
        ) {
            SummaryScope(currentMonth, currentType)
        }
    }
}

/**
 * Composable function for rendering the summary of money exchange scopes for a specific month and type.
 *
 * @param currentMonth The current month information.
 * @param currentType The current type of money exchange (income or expense).
 */
@Composable
fun SummaryScope(currentMonth: MonthInformation, currentType: MoneyExchangeType) {
    LazyColumn {
        items(
            MoneyExchangeScope.entries.toTypedArray()
        ) { value ->
            val svgFile = MoneyExchangeScope.getSVGFile(value)
            val sumValue = MoneyExchangeService
                .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, value)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimens.heightSummaryRow)
                    .padding(horizontal = dimens.padding4, vertical = dimens.padding0),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .border(dimens.border, Color.Black, shape = CircleShape)
                        .size(dimens.imageAboutMonth)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(dimens.padding0),
                    contentAlignment = Alignment.Center,

                ) {
                    Image(
                        painter = painterResource(id = svgFile),
                        contentDescription = SVG_DESCRIPTION
                    )
                }
                Text(
                    text = stringResource(id = value.getLabel()),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimens.fontSizeSummaryRow,
                )
                Text(
                    text = Utilities.formatMoneyValue(sumValue),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimens.fontSizeSummaryRow,
                )
            }
        }
    }
}


/**
 * Preview for the About Month screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AboutMonthScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        AboutMonthScreen(
            month = "example",
            type = "EXPENSE"
        )
    }
}

/**
 * Preview for the About Month screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AboutMonthScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        AboutMonthScreen(
            month = "example",
            type = "EXPENSE"
        )
    }
}

/**
 * Preview for the About Month screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AboutMonthScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        AboutMonthScreen(
            month = "example",
            type = "EXPENSE"
        )
    }
}

/**
 * Preview for the About Month screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AboutMonthScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        AboutMonthScreen(
            month = "example",
            type = "EXPENSE"
        )
    }
}

/**
 * Preview for the About Month screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AboutMonthScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        AboutMonthScreen(
            month = "example",
            type = "EXPENSE"
        )
    }
}

