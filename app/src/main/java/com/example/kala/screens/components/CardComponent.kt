package com.example.kala.screens.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.Green0
import com.example.kala.ui.theme.Red0

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
    val valueColor = if (moneyExchange.type == MoneyExchangeType.EXPENSE) Red0 else Green0
    val valueText = valueSymbol + moneyExchange.getFormattedValue() + "€" //TODO € should be dynamic

    Box(
        modifier = Modifier
            .height(100.dp)
            .width(280.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Button(
            onClick = {
                onAdviceTriggered(moneyExchange.id, moneyExchange.monthAssociated)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp)),
            contentPadding = PaddingValues(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .width(140.dp)
                ) {
                    Text(
                        text = valueText,
                        color = valueColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                    Text(
                        text = moneyExchange.getFormattedDate(),
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }

                Column(
                    modifier = Modifier
                        .width(140.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .border(2.dp, Color.Black, shape = CircleShape)
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(5.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = svgFile),
                            contentDescription = SVG_DESCRIPTION
                        )
                    }

                    Text(
                        text = moneyExchange.scope.toString(),
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
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
