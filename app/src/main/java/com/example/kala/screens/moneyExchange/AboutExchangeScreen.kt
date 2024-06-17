package com.example.kala.screens.moneyExchange

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kala.configuration.ABOUT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.EDIT_EXCHANGE_SCREEN_ROUTE
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
import com.example.kala.screens.components.popups.ConfirmationPopUp
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.Green1
import com.example.kala.ui.theme.Red0
import com.example.kala.ui.theme.dimens

/**
 * Composable function for displaying the About Exchange screen.
 *
 * @param navController The navigation controller.
 * @param monthAssociated The month associated with the exchange.
 * @param exchange The exchange value.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutExchangeScreen(
    navController: NavController? = null,
    monthAssociated: String,
    exchange: Int,
){
    val moneyExchange = MoneyExchangeService.getMoneyExchange(monthAssociated, exchange)

    val svgFile = MoneyExchangeScope.getSVGFile(moneyExchange.scope)
    val valueSymbol = if (moneyExchange.type == MoneyExchangeType.EXPENSE) "-" else "+"
    val valueColor = if (moneyExchange.type == MoneyExchangeType.EXPENSE) Red0 else Green1
    val valueText = valueSymbol + moneyExchange.getFormattedValue() + "€" //TODO € should be dynamic

    var leftButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onLeftTriggered = {
        leftButtonTriggered = true
    }
    var rightButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onRightTriggered = {
        rightButtonTriggered = true
    }

    if (rightButtonTriggered){
        rightButtonTriggered = false
        navController?.navigate(route = "$EDIT_EXCHANGE_SCREEN_ROUTE/$monthAssociated/$exchange")
    }

    if (leftButtonTriggered){
        ConfirmationPopUp(
            onConfirmButton = {
                leftButtonTriggered = false
                MoneyExchangeService.deleteMoneyExchange(monthAssociated, exchange)
                navController?.navigate(route = RECORD_SCREEN_ROUTE)
            },
            onDismissButton = { leftButtonTriggered = false },
        )
    }

    val goBackTriggered = {
        navController?.navigate(route = RECORD_SCREEN_ROUTE)
    }

    Scaffold(
        topBar = {
            Header(
                configuration = HeaderConfiguration.REGISTERED_USER,
                navController = navController,
                triggerScreen = ABOUT_EXCHANGE_SCREEN_ROUTE,
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
            Title(configuration = TitleConfiguration.MORE_INFO)
            Spacer(modifier = Modifier.padding(dimens.space0))
            ValueSection(valueText, valueColor)
            Spacer(modifier = Modifier.padding(dimens.space0))
            ScopeSection(moneyExchange, svgFile)
            Spacer(modifier = Modifier.padding(dimens.space0))
            DateSection(moneyExchange)
            Spacer(modifier = Modifier.padding(dimens.space0))
            DescriptionSection(moneyExchange)
            Spacer(modifier = Modifier.padding(dimens.space0))
            OptionSection(onLeftTriggered, onRightTriggered)
            Spacer(modifier = Modifier.padding(dimens.space4))
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
            .width(dimens.width6)
            .height(dimens.height1)
            .clip(RoundedCornerShape(dimens.rounded))
            .background(Color.White)
            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
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
            .height(dimens.height3)
            .width(dimens.width6)
            .clip(RoundedCornerShape(dimens.rounded))
            .background(Color.White)
            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimens.width2)
                ,
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = stringResource(id = moneyExchange.scope.getLabel()),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimens.width2)
                ,
                contentAlignment = Alignment.Center
            ){
                Box(
                    modifier = Modifier
                        .border(dimens.border, Color.Black, shape = CircleShape)
                        .size(dimens.image3)
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
            .height(dimens.height2)
            .width(dimens.width6)
            .clip(RoundedCornerShape(dimens.rounded))
            .background(Color.White)
            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
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
            .height(dimens.height5)
            .width(dimens.width6)
            .clip(RoundedCornerShape(dimens.rounded))
            .background(Color.White)
            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
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
 * @param onRightTriggered Callback for right button action.
 */
@Composable
private fun OptionSection(onLeftTriggered: () -> Unit, onRightTriggered: () -> Unit) {
    Row {
        MediumButton(configuration = MediumButtonConfiguration.DELETE, onLeftTriggered)
        Spacer(modifier = Modifier.padding(10.dp))
        MediumButton(configuration = MediumButtonConfiguration.EDIT, onRightTriggered)
    }
}

/**
 * Composable function for previewing the AboutExchangeScreen.
 */
@Preview(showBackground = true)
@Composable
fun AboutExchangeScreenPreview(){
    AboutExchangeScreen(monthAssociated =  "example", exchange = 0)
}