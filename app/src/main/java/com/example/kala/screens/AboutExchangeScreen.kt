package com.example.kala.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kala.configuration.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.MediumButtonConfiguration
import com.example.kala.configuration.RECORD_SCREEN_ROUTE
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.model.MoneyExchangeService
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.buttons.MediumButton
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for displaying the About Exchange screen.
 *
 * @param navController The navigation controller.
 * @param monthAssociated The month associated with the exchange.
 * @param exchange The exchange value.
 */
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutExchangeScreen(
    navController: NavController? = null,
    monthAssociated: String,
    exchange: Int,
){
    val moneyExchangeService = MoneyExchangeService()
    val moneyExchange = moneyExchangeService.getMoneyExchange(monthAssociated, exchange)

    val svgFile = MoneyExchangeScope.getSVGFile(moneyExchange.scope)
    val valueSymbol = if (moneyExchange.type == MoneyExchangeType.EXPENSE) "-" else "+"
    val valueColor = if (moneyExchange.type == MoneyExchangeType.EXPENSE) Color.Red else Color.Green
    val valueText = valueSymbol + moneyExchange.getFormattedValue() + "€" //TODO € should be dynamic

    var leftButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onLeftTriggered = {
        leftButtonTriggered = true
    }
    var rigthButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onRigthTriggered = {
        rigthButtonTriggered = true
    }

    if (rigthButtonTriggered){
        // TODO Send the money exchange
        navController?.navigate(route = ADD_EXCHANGE_SCREEN_ROUTE)
    }

    if (leftButtonTriggered){
        // TODO Delete the money exchange
        navController?.navigate(route = RECORD_SCREEN_ROUTE)
    }

    Scaffold(
        topBar = {
            Header(configuration = HeaderConfiguration.REGISTERED_USER, navController)
        },
        bottomBar = {
            Footer(configuration = FooterConfiguration.BACK_AND_HOME, navController)
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(50.dp))
            Title(configuration = TitleConfiguration.MORE_INFO)
            Spacer(modifier = Modifier.padding(10.dp))
            ValueSection(valueText, valueColor)
            Spacer(modifier = Modifier.padding(10.dp))
            ScopeSection(moneyExchange, svgFile)
            Spacer(modifier = Modifier.padding(10.dp))
            DateSection(moneyExchange)
            Spacer(modifier = Modifier.padding(10.dp))
            DescriptionSection(moneyExchange)
            Spacer(modifier = Modifier.padding(10.dp))
            OptionSection(onLeftTriggered, onRigthTriggered)
            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}

/**
 * Composable function for displaying the value section.
 *
 * @param valueText The text to display.
 * @param valueColor The color of the text.
 */
@Composable
private fun ValueSection(valueText: String, valueColor: Color) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
        ,
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = valueText,
            color = valueColor,
            modifier = Modifier
                .fillMaxWidth()
            ,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        )
    }
}

/**
 * Composable function for displaying the scope section.
 *
 * @param moneyExchange The money exchange object.
 * @param svgFile The SVG file associated with the scope.
 */
@Composable
private fun ScopeSection(moneyExchange: MoneyExchange, svgFile: Int) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(125.dp)
                ,
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = moneyExchange.scope.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(125.dp)
                ,
                contentAlignment = Alignment.Center
            ){
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
            }

        }
    }
}


/**
 * Composable function for displaying the date section.
 *
 * @param moneyExchange The money exchange object.
 */
@Composable
private fun DateSection(moneyExchange: MoneyExchange) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = moneyExchange.getFormattedDate(),
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    }

}

/**
 * Composable function for displaying the description section.
 *
 * @param moneyExchange The money exchange object.
 */
@Composable
private fun DescriptionSection(moneyExchange: MoneyExchange) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        moneyExchange.description?.let { it1 ->
            Text(
                text = it1,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
            )
        }
    }
}

/**
 * Composable function for displaying the option section.
 *
 * @param onLeftTriggered Callback for left button action.
 * @param onRigthTriggered Callback for right button action.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun OptionSection(onLeftTriggered: () -> Unit, onRigthTriggered: () -> Unit) {
    Row {
        MediumButton(configuration = MediumButtonConfiguration.DELETE, onLeftTriggered)
        Spacer(modifier = Modifier.padding(10.dp))
        MediumButton(configuration = MediumButtonConfiguration.EDIT, onRigthTriggered)
    }
}

/**
 * Composable function for previewing the AboutExchangeScreen.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AboutExchangeScreenPreview(){
    AboutExchangeScreen(monthAssociated =  "example", exchange = 0)
}