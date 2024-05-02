package com.example.kala.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.configuration.ABOUT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.RECORD_SCREEN_ROUTE
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.entities.MoneyExchange
import com.example.kala.model.MoneyExchangeService
import com.example.kala.screens.components.Card
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering the Record screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecordScreen(
    navController: NavController? = null
){
    val moneyExchangeList: List<MoneyExchange> = MoneyExchangeService.getAllMoneyExchanges()

    var adviceTriggered by remember { mutableStateOf(false) }
    var cardSelected by remember {
        mutableIntStateOf(-1)
    }
    var monthSelected by remember {
        mutableStateOf("")
    }
    val onAdviceTriggered: (Int, String) -> Unit = { idExchange, idMonth ->
        adviceTriggered = true
        cardSelected = idExchange
        monthSelected = idMonth
    }

    if (adviceTriggered){
        adviceTriggered = false
        navController?.navigate(route = "$ABOUT_EXCHANGE_SCREEN_ROUTE/$monthSelected/$cardSelected")
    }

    val goBackTriggered = {
        navController?.navigate(route = HOME_SCREEN_ROUTE)
    }

    Scaffold(
        topBar = {
            Header(
                configuration = HeaderConfiguration.REGISTERED_USER,
                navController = navController,
                triggerScreen = RECORD_SCREEN_ROUTE,
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
            Title(configuration = TitleConfiguration.RECORD)
            RecordBody(moneyExchangeList, onAdviceTriggered)
            Spacer(modifier = Modifier.padding(dimens.space4))
        }
    }
}

/**
 * Composable function for rendering the body content of the Record screen.
 */
@Composable
private fun RecordBody(
    moneyExchangeList: List<MoneyExchange>,
    onAdviceTriggered: (Int, String) -> Unit
) {
    Spacer(modifier = Modifier.padding(dimens.space1))
    if (moneyExchangeList.isEmpty()){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            EmptyAdvice()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            items(moneyExchangeList) { value ->
                println(value.toString())
                Card(value, onAdviceTriggered)
                Spacer(modifier = Modifier.padding(dimens.space0))
            }
        }
    }
}

@Composable
fun EmptyAdvice(){
    Box(
        modifier = Modifier
            .height(dimens.height4)
            .width(dimens.width8)
            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
            .background(Color.White, RoundedCornerShape(dimens.rounded))
            .padding(dimens.padding3)
    ) {
        Text(
            text = "No money exchange has been added",
            color = Color.Black,
            modifier = Modifier
                .fillMaxSize()
            ,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = dimens.fontSize3,
        )
    }
}

/**
 * Preview function for testing and visualizing the Record screen.
 */
@Preview(showBackground = true)
@Composable
fun RecordScreenPreview(){
    RecordScreen()
}
