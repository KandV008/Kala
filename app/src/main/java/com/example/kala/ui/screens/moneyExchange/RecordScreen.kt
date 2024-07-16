package com.example.kala.ui.screens.moneyExchange

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.R
import com.example.kala.model.MoneyExchangeService
import com.example.kala.model.entities.MoneyExchange
import com.example.kala.ui.components.Card
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.LayoutWithNoScroll
import com.example.kala.ui.screens.navigation.ABOUT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.RECORD_SCREEN_ROUTE
import com.example.kala.ui.theme.dimens

val NO_MONEY_EXCHANGE_ADDED = R.string.no_money_exchange_added

/**
 * Composable function for rendering the Record screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecordScreen(
    navController: NavController? = null
) {
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

    if (adviceTriggered) {
        adviceTriggered = false
        navController?.navigate(route = "$ABOUT_EXCHANGE_SCREEN_ROUTE/$monthSelected/$cardSelected")
    }

    LayoutWithNoScroll(
        navController = navController,
        headerConfiguration = HeaderConfiguration.REGISTERED_USER,
        triggerScreen = RECORD_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_HOME,
        onAdviceTriggered = {}
    ) {
        Title(configuration = TitleConfiguration.RECORD)
        RecordBody(moneyExchangeList, onAdviceTriggered)
    }
}

/**
 * Callback function triggered when an item/card is selected to navigate to the About Exchange screen.
 *
 * @param idExchange The id of the selected money exchange.
 * @param idMonth The id of the selected month.
 */
@Composable
private fun RecordBody(
    moneyExchangeList: List<MoneyExchange>,
    onAdviceTriggered: (Int, String) -> Unit
) {
    Spacer(modifier = Modifier.padding(dimens.space1))
    if (moneyExchangeList.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
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

/**
 * Composable function for displaying advice when there are no money exchanges added.
 */
@Composable
fun EmptyAdvice() {
    Box(
        modifier = Modifier
            .height(dimens.height4)
            .width(dimens.width8)
            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
            .background(Color.White, RoundedCornerShape(dimens.rounded))
            .padding(dimens.padding3)
    ) {
        Text(
            text = stringResource(id = NO_MONEY_EXCHANGE_ADDED),
            color = Color.Black,
            modifier = Modifier
                .fillMaxSize(),
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
fun RecordScreenPreview() {
    RecordScreen()
}
