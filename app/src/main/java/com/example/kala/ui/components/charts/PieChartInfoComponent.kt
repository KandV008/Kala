package com.example.kala.ui.components.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.kala.ui.components.buttons.ChartButtonConfiguration
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.model.entities.MonthInformation
import com.example.kala.model.MoneyExchangeService
import com.example.kala.ui.components.buttons.ChartButton
import com.example.kala.model.MonthInformationService
import com.example.kala.ui.screens.utilities.Utilities.getMonthString
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

@Composable
fun PieChartInfo(
    month: String,
    type: String,
    onLeftTriggered: () -> Unit = {},
    onRightTriggered: () -> Unit = {},
){
    val currentMonth = MonthInformationService.getMonthInformation(month)
    val currentType = MoneyExchangeType.valueOf(type)
    val showPieChart = if (MoneyExchangeType.INCOME == currentType)
        currentMonth.incomeMoney != 0.0
    else
        currentMonth.expensedMoney != 0.0

    Box(
        modifier = Modifier
            .height(dimens.height8)
            .width(dimens.width9)
            .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(dimens.rounded))
                .background(Color.White)
                .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
                .padding(dimens.padding5),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PieChartHeader(currentMonth, onLeftTriggered, onRightTriggered)
            Spacer(modifier = Modifier.weight(1F))
            Spacer(modifier = Modifier.padding(dimens.padding3))
            Box(
                modifier = Modifier
                    .size(dimens.height7)
            ){
                if (showPieChart){
                    PieChartBody(currentMonth, currentType)
                } else {
                    EmptyBarChartAdvice()
                }
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}

@Composable
fun PieChartHeader(
    currentMonth: MonthInformation,
    onLeftTriggered: () -> Unit = {},
    onRightTriggered: () -> Unit = {},
){
    val leftChartButtonAlpha: Int = MonthInformationService.hasPrevMonth(currentMonth)
    val rightChartButtonAlpha: Int = MonthInformationService.hasNextMonth(currentMonth)

    Row(
        modifier = Modifier
            .height(dimens.height0)
            .width(dimens.width6),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ChartButton(
            configuration = ChartButtonConfiguration.LEFT,
            alpha = leftChartButtonAlpha.toFloat(),
            onAdviceTriggered = onLeftTriggered,
        )
        Text(
            text = stringResource(id = getMonthString(currentMonth.dateCreation.month)),
            color = Color.Black,
            fontSize = dimens.fontSize3,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        ChartButton(
            configuration = ChartButtonConfiguration.RIGHT,
            alpha = rightChartButtonAlpha.toFloat(),
            onAdviceTriggered = onRightTriggered,
        )
    }
}

@Composable
fun PieChartBody(
    currentMonth: MonthInformation,
    currentType: MoneyExchangeType
){
    val pieChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice(
                label = stringResource(id = MoneyExchangeScope.FOOD.getLabel()),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.FOOD)
                    .toFloat(),
                color = MoneyExchangeScope.FOOD.getColor()
            ),
            PieChartData.Slice(
                label = stringResource(id = MoneyExchangeScope.LEISURE.getLabel()),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.LEISURE)
                    .toFloat(),
                color = MoneyExchangeScope.LEISURE.getColor()
            ),
            PieChartData.Slice(
                label = stringResource(id = MoneyExchangeScope.USEFUL.getLabel()),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.USEFUL)
                    .toFloat(),
                color = MoneyExchangeScope.USEFUL.getColor()
            ),
            PieChartData.Slice(
                label = stringResource(id = MoneyExchangeScope.MEDICINE.getLabel()),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.MEDICINE)
                    .toFloat(),
                color = MoneyExchangeScope.MEDICINE.getColor()
            ),
            PieChartData.Slice(
                label = stringResource(id = MoneyExchangeScope.OTHER.getLabel()),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.OTHER)
                    .toFloat(),
                color = MoneyExchangeScope.OTHER.getColor()
            ),
        ),
        plotType = PlotType.Pie
    )

    val pieChartConfig = PieChartConfig(
        sliceLabelTextColor = Color.Black,
        sliceLabelTextSize = dimens.fontSize0

    )

    PieChart(
        modifier = Modifier
        ,
        pieChartData = pieChartData,
        pieChartConfig = pieChartConfig
    )
}

/**
 * Composable function for previewing the Chart component.
 * This preview function is used for testing and visualizing the Chart component.
 */
@Preview
@Composable
fun PieChartInfoPreview() {
    val idMonth = "example"

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(BoneWhite),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        PieChartInfo(month = idMonth, type = "EXPENSE")
    }
}