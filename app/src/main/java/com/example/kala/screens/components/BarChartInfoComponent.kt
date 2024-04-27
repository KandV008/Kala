package com.example.kala.screens.components

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import com.example.kala.configuration.ChartButtonConfiguration
import com.example.kala.configuration.ChartConfiguration
import com.example.kala.entities.MonthInformation
import com.example.kala.model.MoneyExchangeService
import com.example.kala.screens.components.buttons.ChartButton
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.Green0
import com.example.kala.ui.theme.Red0
import com.example.kala.ui.theme.Yellow0
import com.example.kala.ui.theme.dimens

@Composable
fun BarChartInfo(
    configuration: ChartConfiguration,
    month: String,
    onLeftTriggered: () -> Unit = {},
    onRightTriggered: () -> Unit = {},
){
    val currentMonth = MoneyExchangeService.getMonthInformation(month)

    Box(
        modifier = Modifier
            .height(dimens.height9)
            .width(dimens.width9)
            .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(dimens.rounded))
                .background(Color.White)
                .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
                .padding(dimens.padding4),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BarChartHeader(configuration, currentMonth, onLeftTriggered, onRightTriggered)
            Spacer(modifier = Modifier.weight(1F))
            Spacer(modifier = Modifier.padding(dimens.space1))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .size(dimens.height7)
            ){
                BarChartBody(currentMonth)
            }
            Spacer(modifier = Modifier.weight(1F))
            BarChartFooter(currentMonth)
        }
    }
}

@Composable
fun BarChartHeader(
    configuration: ChartConfiguration,
    currentMonth: MonthInformation,
    onLeftTriggered: () -> Unit = {},
    onRightTriggered: () -> Unit = {},
){
    val leftChartButtonAlpha: Int = MoneyExchangeService.hasNextMonth(currentMonth)
    val rightChartButtonAlpha: Int = MoneyExchangeService.hasPrevMonth(currentMonth)

    Row(
        modifier = Modifier
            .height(dimens.height0)
            .width(dimens.width6),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ChartButton(
            configuration = ChartButtonConfiguration.LEFT,
            alpha = configuration.alpha() * leftChartButtonAlpha,
            onAdviceTriggered = onLeftTriggered,
        )
        Text(
            text = currentMonth.dateCreation.month.toString(),
            color = Color.Black,
            fontSize = dimens.fontSize3,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        ChartButton(
            configuration = ChartButtonConfiguration.RIGHT,
            alpha = configuration.alpha() * rightChartButtonAlpha,
            onAdviceTriggered = onRightTriggered,
        )
    }
}

@Composable
fun BarChartBody(
    currentMonth: MonthInformation
){
    val barsData = listOf(
        BarData(Point(0F, 0F), Color.White),
        BarData(Point(1.75F, currentMonth.incomeMoney.toFloat()), Green0),
        BarData(Point(2.5F, currentMonth.expensedMoney.toFloat()), Red0),
        BarData(Point(4F, 0F), Color.White),
        )

    val xAxisData = AxisData.Builder()
        .axisLineColor(Color.Black)
        .axisLineThickness(5.dp)
        .backgroundColor(Color.White)
        .build()

    val yAxisData = AxisData.Builder()
        .labelAndAxisLinePadding(1.dp)
        .axisLineColor(Color.White)
        .backgroundColor(Color.White)
        .build()

    val barChartData = BarChartData(
        chartData = barsData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = Color.White,
        showYAxis = false,
        showXAxis = false,
        paddingTop = 0.dp,
    )

    BarChart(
        modifier = Modifier
            .background(Color.White)
        ,
        barChartData = barChartData,
    )
}

@Composable
fun BarChartFooter(
    currentMonth: MonthInformation
){
    val incomeValue = currentMonth.incomeMoney
    val expenseValue = currentMonth.expensedMoney
    val balanceValue = incomeValue - expenseValue

    Row(
        modifier = Modifier
            .height(dimens.height1)
            .width(dimens.width8),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BarChartSummary(title = "Income", value = "+$incomeValue", color = Green0)
        BarChartSummary(
            title = "Balance",
            value = if (balanceValue >= 0) "+$balanceValue" else balanceValue.toString(),
            color = Yellow0
        )
        BarChartSummary(title = "Expense", value = "-$expenseValue", color = Red0)
    }
}

/**
 * Composable function for rendering chart information.
 *
 * @param title The title of the chart information.
 * @param value The value of the chart information.
 * @param color The color associated with the chart information.
 */
@Composable
fun BarChartSummary(title: String, value: String, color: Color){
    Column (
        modifier = Modifier
            .height(dimens.height3)
            .width(dimens.width1),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = title,
            color = color,
            fontSize = dimens.fontSize0,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            color = color,
            fontSize = dimens.fontSize0,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Composable function for previewing the Chart component.
 * This preview function is used for testing and visualizing the Chart component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun BarChartInfoPreview() {
    val idMonth = "example"

    Scaffold {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            items(ChartConfiguration.entries.toTypedArray()){
                    value ->
                BarChartInfo(configuration = value, month = idMonth)
                Spacer(modifier = Modifier.padding(dimens.space1))
            }
        }
    }
}
