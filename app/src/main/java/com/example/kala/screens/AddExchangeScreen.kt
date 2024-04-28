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
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.model.MoneyExchangeService
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.InvalidFormPopUp
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.inputs.BigTextInput
import com.example.kala.screens.components.inputs.MenuInput
import com.example.kala.screens.components.inputs.NumberInput
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

val errorMessageList: MutableList<String> = mutableListOf()

private const val INVALID_VALUE_ERROR_MESSAGE = "Value of the exchange must be higher than Zero"
private const val INVALID_TYPE_ERROR_MESSAGE = "Invalid Type of the exchange"
private const val INVALID_SCOPE_ERROR_MESSAGE = "Invalid Scope of the exchange"

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
    var isPopUpVisible by remember { mutableStateOf(false) }
    val hidePopUp: () -> Unit = {
        isPopUpVisible = false
    }

    var adviceTriggered by remember { mutableStateOf(false) }

    var valueExchange by remember { mutableStateOf("") }
    var typeExchange by remember { mutableStateOf("") }
    var scopeExchange by remember { mutableStateOf("") }
    var descriptionExchange by remember { mutableStateOf("") }

    val updateValueExchange: (String) -> Unit = { newValue ->
        val replace = newValue.replace(",", ".")
        valueExchange = replace
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

    if (adviceTriggered) {
        adviceTriggered = false
        errorMessageList.clear()
        val validForm = isValidForm(valueExchange, typeExchange, scopeExchange)

        if (validForm){
            val newMoneyExchange = MoneyExchange(
                valueExchange.toDouble(),
                typeExchange,
                scopeExchange,
                descriptionExchange
            )
            MoneyExchangeService.addMoneyExchange(newMoneyExchange)
            navController?.navigate(route = HOME_SCREEN_ROUTE)
        } else {
            isPopUpVisible = true
        }
    }

    if(isPopUpVisible){
        InvalidFormPopUp(messageList = errorMessageList, onConfirmButton = hidePopUp)
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
 * Checks if the provided values for exchange are valid.
 *
 * @param valueExchange The value of the exchange as a string.
 * @param typeExchange The type of exchange as a string.
 * @param scopeExchange The scope of exchange as a string.
 * @return `true` if all values are valid, `false` otherwise.
 */
fun isValidForm(
    valueExchange: String,
    typeExchange: String,
    scopeExchange: String,
): Boolean {
    try {
        val toDouble = valueExchange.toDouble()
        if (toDouble <= 0) {
            errorMessageList.add(INVALID_VALUE_ERROR_MESSAGE)
        }
    } catch (e: NumberFormatException){
        errorMessageList.add(INVALID_VALUE_ERROR_MESSAGE)
    }

    try {
        MoneyExchangeType.valueOf(typeExchange)
    } catch (e: IllegalArgumentException) {
        errorMessageList.add(INVALID_TYPE_ERROR_MESSAGE)
    }

    try {
        MoneyExchangeScope.valueOf(scopeExchange)
    } catch (e: IllegalArgumentException) {
        errorMessageList.add(INVALID_SCOPE_ERROR_MESSAGE)
    }

    return errorMessageList.isEmpty()
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
