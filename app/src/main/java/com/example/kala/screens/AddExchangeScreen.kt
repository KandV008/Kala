package com.example.kala.screens

import android.annotation.SuppressLint
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
import com.example.kala.ui.theme.dimens

/**
 * Composable function for adding a new money exchange.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddExchangeScreen(
    navController: NavController? = null
) {
    var adviceTriggered by remember { mutableStateOf(false) }

    var valueExchange by remember { mutableStateOf("") }
    var typeExchange by remember { mutableStateOf("") }
    var scopeExchange by remember { mutableStateOf("") }
    var descriptionExchange by remember { mutableStateOf("") }

    val updateValueExchange: (String) -> Unit = { newValue ->
        valueExchange = newValue
    }
    val updateTypeExchange: (String) -> Unit = { newValue ->
        typeExchange = newValue
    }
    val updateScopeExchange: (String) -> Unit = { newValue ->
        scopeExchange = newValue
    }
    val updateDescriptionExchange: (String) -> Unit = { newValue ->
        descriptionExchange = newValue
    }

    if (adviceTriggered) { // TODO Validate form
        val newMoneyExchange = MoneyExchange(
            valueExchange.toDouble(),
            typeExchange,
            scopeExchange,
            descriptionExchange
        )
        MoneyExchangeService.addMoneyExchange(newMoneyExchange)
        adviceTriggered = false
        navController?.navigate(route = HOME_SCREEN_ROUTE)
    }

    Scaffold(
        topBar = {
            Header(configuration = HeaderConfiguration.REGISTERED_USER, navController)
        },
        bottomBar = {
            Footer(configuration = FooterConfiguration.ALL, navController) {
                adviceTriggered = true
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(dimens.space4))
            Title(configuration = TitleConfiguration.ADD_EXCHANGE)

            Spacer(modifier = Modifier.padding(dimens.space1))
            NumberInput(valueInput = valueExchange, onValueChange = updateValueExchange)

            Spacer(modifier = Modifier.padding(dimens.space0))
            MenuInput(
                configuration = MenuInputConfiguration.TYPE,
                valueInput = typeExchange,
                onValueChange = updateTypeExchange
            )

            Spacer(modifier = Modifier.padding(dimens.space0))
            MenuInput(
                configuration = MenuInputConfiguration.SCOPE,
                valueInput = scopeExchange,
                onValueChange = updateScopeExchange
            )

            Spacer(modifier = Modifier.padding(dimens.space0))
            BigTextInput(valueInput = descriptionExchange, onValueChange = updateDescriptionExchange)
            Spacer(modifier = Modifier.padding(dimens.space4))
        }
    }
}

/**
 * Composable function for previewing adding a new money exchange.
 *
 */
@Preview(showBackground = true)
@Composable
fun AddExchangeScreenPreview() {
    AddExchangeScreen()
}
