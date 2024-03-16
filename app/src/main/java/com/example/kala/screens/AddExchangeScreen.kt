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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AddExchangeScreen(
    navController: NavController? = null
){
    val moneyExchangeService = MoneyExchangeService()

    var adviceTriggered by remember { mutableStateOf(false) }
    val onAdviceTriggered = {
        adviceTriggered = true
    }
    var valueExchange by remember { mutableStateOf("") }
    var typeExchange by remember { mutableStateOf("") }
    var scopeExchange by remember { mutableStateOf("") }
    var descriptionExchange by remember { mutableStateOf("") }

    fun validateForm(): Boolean{
        val valueExchangeValidation = valueExchange.toDouble() > 0
        val typeExchangeValidation = typeExchange != ""
        val scopeExchangeValidation = scopeExchange != ""
        return valueExchangeValidation && typeExchangeValidation && scopeExchangeValidation
    }

    if (adviceTriggered && validateForm()) {
        // TODO Alert the wrong text fields
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
            NumberInput(valueInput = valueExchange)
            { newValue -> valueExchange = newValue }

            Spacer(modifier = Modifier.padding(10.dp))
            MenuInput(
                configuration = MenuInputConfiguration.TYPE,
                valueInput = typeExchange)
            { newValue -> typeExchange = newValue }

            Spacer(modifier = Modifier.padding(10.dp))
            MenuInput(
                configuration = MenuInputConfiguration.SCOPE,
                valueInput = scopeExchange
            ){ newValue -> scopeExchange = newValue }

            Spacer(modifier = Modifier.padding(10.dp))
            BigTextInput(valueInput = descriptionExchange)
            { newValue -> descriptionExchange = newValue }

            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun AddExchangeScreenPreview(){
    AddExchangeScreen()
}
