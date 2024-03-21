package com.example.kala.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.MenuInputConfiguration
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.entities.MoneyExchange
import com.example.kala.model.MoneyExchangeService
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.inputs.BigTextInput
import com.example.kala.screens.components.inputs.MenuInput
import com.example.kala.screens.components.inputs.NumberInput
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for rendering the Add Exchange screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddExchangeScreen(
    navController: NavController? = null
){
    val moneyExchangeService = MoneyExchangeService()

    var adviceTriggered by remember { mutableStateOf(false) }
    val onAdviceTriggered = {
        adviceTriggered = true
    }
    val valueExchange by remember { mutableStateOf("") }
    val typeExchange by remember { mutableStateOf("") }
    val scopeExchange by remember { mutableStateOf("") }
    val descriptionExchange by remember { mutableStateOf("") }

    fun validateForm(): Boolean{
        val valueExchangeValidation = valueExchange.toDouble() > 0
        val typeExchangeValidation = typeExchange.isNotBlank()
        val scopeExchangeValidation = scopeExchange.isNotBlank()
        return valueExchangeValidation && typeExchangeValidation && scopeExchangeValidation
    }

    if (adviceTriggered && validateForm()) {
        val newMoneyExchange = MoneyExchange(valueExchange.toDouble(), typeExchange, scopeExchange, descriptionExchange)
        moneyExchangeService.addMoneyExchange(newMoneyExchange)
        navController?.navigate(route = HOME_SCREEN_ROUTE)
    }

    Scaffold(
        topBar = {
            Header(configuration = HeaderConfiguration.REGISTERED_USER, navController)
        },
        bottomBar = {
            Footer(configuration = FooterConfiguration.ALL, navController, onAdviceTriggered)
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(50.dp))
            Title(configuration = TitleConfiguration.ADD_EXCHANGE)

            Spacer(modifier = Modifier.padding(15.dp))
            MoneyExchangeForm(valueExchange, typeExchange, scopeExchange, descriptionExchange)

            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}

/**
 * Composable function for rendering the form for entering money exchange details.
 *
 * @param valueExchange The value of the exchange.
 * @param typeExchange The type of the exchange.
 * @param scopeExchange The scope of the exchange.
 * @param descriptionExchange The description of the exchange.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun MoneyExchangeForm(
    valueExchange: String,
    typeExchange: String,
    scopeExchange: String,
    descriptionExchange: String
) {
    var valueExchange1 = valueExchange
    var typeExchange1 = typeExchange
    var scopeExchange1 = scopeExchange
    var descriptionExchange1 = descriptionExchange
    NumberInput(valueInput = valueExchange1)
    { newValue -> valueExchange1 = newValue }

    Spacer(modifier = Modifier.padding(10.dp))
    MenuInput(
        configuration = MenuInputConfiguration.TYPE,
        valueInput = typeExchange1
    )
    { newValue -> typeExchange1 = newValue }

    Spacer(modifier = Modifier.padding(10.dp))
    MenuInput(
        configuration = MenuInputConfiguration.SCOPE,
        valueInput = scopeExchange1
    ) { newValue -> scopeExchange1 = newValue }

    Spacer(modifier = Modifier.padding(10.dp))
    BigTextInput(valueInput = descriptionExchange1)
    { newValue -> descriptionExchange1 = newValue }
}

/**
 * Composable function for previewing the Add Exchange screen.
 * This preview function is used for testing and visualizing the Add Exchange screen.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddExchangeScreenPreview(){
    AddExchangeScreen()
}

