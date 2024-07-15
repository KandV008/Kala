package com.example.kala.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.ui.screens.utilities.Utilities
import com.example.kala.model.entities.MoneyExchange
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.ui.screens.utilities.Utilities.getFormattedDate
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.Green1
import com.example.kala.ui.theme.Red0
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering a card representing a money exchange item.
 *
 * @param moneyExchange The money exchange item to display.
 * @param onAdviceTriggered Callback function invoked when the advice is triggered.
 */
@Composable
fun Card(
    moneyExchange: MoneyExchange,
    onAdviceTriggered: (Int, String) -> Unit
){
    val svgFile = MoneyExchangeScope.getSVGFile(moneyExchange.scope)
    val valueSymbol = if (moneyExchange.type == MoneyExchangeType.EXPENSE) "-" else "+"
    val valueColor = if (moneyExchange.type == MoneyExchangeType.EXPENSE) Red0 else Green1
    val valueText = valueSymbol + Utilities.formatMoneyValue(moneyExchange.value)

    Box(
        modifier = Modifier
            .height(dimens.height4)
            .width(dimens.width8)
            .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
    ) {
        Button(
            onClick = {
                onAdviceTriggered(moneyExchange.id, moneyExchange.monthAssociated)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxSize()
                .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
                .background(Color.White, RoundedCornerShape(dimens.rounded)),
            contentPadding = PaddingValues(dimens.padding3)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .width(dimens.width4)
                ) {
                    Text(
                        text = valueText,
                        color = valueColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimens.height1),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = dimens.fontSize3,
                    )
                    Text(
                        text = getFormattedDate(moneyExchange.date),
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = dimens.fontSize0,
                    )
                }

                Column(
                    modifier = Modifier
                        .width(dimens.width4),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .border(dimens.border, Color.Black, shape = CircleShape)
                            .size(dimens.image3)
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
                        text = stringResource(id = moneyExchange.scope.getLabel()),
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = dimens.fontSize0,
                    )
                }
            }
        }
    }
}

/**
 * Composable function for rendering a preview of the Card component.
 * This preview function is used for testing and visualizing the Card component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun CardPreview(){
    // Initializing state variables
    var adviceTriggered by remember { mutableStateOf(false) }
    var cardSelected by remember { mutableIntStateOf(-1) }
    var monthSelected by remember { mutableStateOf("") }

    // Callback function for when advice is triggered
    val onAdviceTriggered: (Int, String) -> Unit = { idExchange, idMonth ->
        adviceTriggered = true
        cardSelected = idExchange
        monthSelected = idMonth
    }

    // Sample data for the money exchange
    val value = 30.0
    val type: MoneyExchangeType = MoneyExchangeType.EXPENSE
    val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
    val description = "Weekly purchase"
    val moneyExchange = MoneyExchange(value, type, scope, description)

    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Card(moneyExchange, onAdviceTriggered)
        }
    }
}
