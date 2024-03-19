package com.example.kala.screens.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Card(
    moneyExchange: MoneyExchange,
    onAdviceTriggered: () -> Unit = {}
){
    val svgFile = MoneyExchangeScope.getSVGFile(moneyExchange.scope)
    val valueSymbol = if (moneyExchange.type == MoneyExchangeType.EXPENSE) "-" else "+"
    val valueColor = if (moneyExchange.type == MoneyExchangeType.EXPENSE) Color.Red else Color.Green
    val valueText = valueSymbol + moneyExchange.getFormattedValue() + "€" //TODO € should be dynamic
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(280.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Button(
            onClick = {
                onAdviceTriggered()
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
                            .height(50.dp)
                        ,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                    Text(
                        text = moneyExchange.getFormattedDate(),
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }

                Column(
                    modifier = Modifier
                        .width(140.dp)
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .border(2.dp, Color.Black, shape = CircleShape)
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(5.dp)
                        ,
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
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun CardPreview(){
    var onAdvice = false;
    val value = 30.0
    val type: MoneyExchangeType = MoneyExchangeType.EXPENSE
    val scope: MoneyExchangeScope = MoneyExchangeScope.FOOD
    val description = "Compra semanal"
    val moneyExchange = MoneyExchange(value, type, scope, description)

    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Card(moneyExchange, ){ onAdvice = true}
        }
    }
}