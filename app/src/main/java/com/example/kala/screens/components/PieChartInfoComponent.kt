package com.example.kala.screens.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.kala.configuration.ChartButtonConfiguration
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.entities.MonthInformation
import com.example.kala.model.MoneyExchangeService
import com.example.kala.screens.components.buttons.ChartButton
import com.example.kala.ui.theme.BoneWhite

@Composable
fun PieChartInfo(
    month: String,
    type: String,
    onLeftTriggered: () -> Unit = {},
    onRightTriggered: () -> Unit = {},
){
    val currentMonth = MoneyExchangeService.getMonthInformation(month)
    val currentType = MoneyExchangeType.valueOf(type)

    Box(
        modifier = Modifier
            .height(370.dp)
            .width(320.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
    ){
        Column(
            modifier = Modifier
                .height(370.dp)
                .width(320.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PieChartHeader(currentMonth, onLeftTriggered, onRightTriggered)
            Spacer(modifier = Modifier.weight(1F))
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxSize()
            ){
                PieChartBody(currentMonth, currentType)
            }
            Spacer(modifier = Modifier.weight(1F))
        }
    }
}

@Composable
fun PieChartHeader(
    currentMonth: MonthInformation,
    onLeftTriggered: () -> Unit = {},
    onRigthTriggered: () -> Unit = {},
){
    val leftChartButtonAlpha: Int = MoneyExchangeService.hasNextMonth(currentMonth)
    val rightChartButtonAlpha: Int = MoneyExchangeService.hasPrevMonth(currentMonth)

    Row(
        modifier = Modifier
            .height(50.dp)
            .width(250.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ChartButton(
            configuration = ChartButtonConfiguration.LEFT,
            alpha = leftChartButtonAlpha.toFloat(),
            onAdviceTriggered = onLeftTriggered,
        )
        Text(
            text = currentMonth.dateCreation.month.toString(),
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        ChartButton(
            configuration = ChartButtonConfiguration.RIGHT,
            alpha = rightChartButtonAlpha.toFloat(),
            onAdviceTriggered = onRigthTriggered,
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
                label = MoneyExchangeScope.FOOD.toString(),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.FOOD)
                    .toFloat(),
                color = MoneyExchangeScope.FOOD.getColor()
            ),
            PieChartData.Slice(
                label = MoneyExchangeScope.LEISURE.toString(),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.LEISURE)
                    .toFloat(),
                color = MoneyExchangeScope.LEISURE.getColor()
            ),
            PieChartData.Slice(
                label = MoneyExchangeScope.USEFUL.toString(),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.USEFUL)
                    .toFloat(),
                color = MoneyExchangeScope.USEFUL.getColor()
            ),
            PieChartData.Slice(
                label = MoneyExchangeScope.MEDICINE.toString(),
                value = MoneyExchangeService
                    .getSumOfMoneyExchangeByScopeAndType(currentMonth, currentType, MoneyExchangeScope.MEDICINE)
                    .toFloat(),
                color = MoneyExchangeScope.MEDICINE.getColor()
            ),
            PieChartData.Slice(
                label = MoneyExchangeScope.OTHER.toString(),
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
        sliceLabelTextSize = 20.sp

    )

    PieChart(
        modifier = Modifier
            .padding(10.dp)
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