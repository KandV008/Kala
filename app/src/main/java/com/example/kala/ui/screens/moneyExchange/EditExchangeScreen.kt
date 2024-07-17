package com.example.kala.ui.screens.moneyExchange

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.model.MoneyExchangeService
import com.example.kala.model.entities.MoneyExchange
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.components.inputs.BigTextInput
import com.example.kala.ui.components.inputs.MenuInput
import com.example.kala.ui.components.inputs.MenuInputConfiguration
import com.example.kala.ui.components.inputs.NumberInput
import com.example.kala.ui.components.popUps.InvalidFormPopUp
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.EDIT_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.FormValidation.isValidForm
import com.example.kala.ui.screens.utilities.errorMessageList
import com.example.kala.ui.theme.dimens

/**
 * Composable function for editing a money exchange.
 *
 * @param navController The navigation controller.
 * @param monthAssociated The month associated with the money exchange.
 * @param exchange The index of the money exchange.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditExchangeScreen(
    navController: NavController? = null,
    monthAssociated: String,
    exchange: Int,
) {
    var isPopUpVisible by remember { mutableStateOf(false) }
    val hidePopUp: () -> Unit = {
        isPopUpVisible = false
    }

    val moneyExchange = MoneyExchangeService.getMoneyExchange(monthAssociated, exchange)

    var adviceTriggered by remember { mutableStateOf(false) }

    var valueExchange by remember { mutableStateOf(moneyExchange.value.toString()) }
    var typeExchange by remember { mutableStateOf(moneyExchange.type.toString()) }
    var showTypeExchange by remember { mutableIntStateOf(moneyExchange.type.getLabel()) }
    var scopeExchange by remember { mutableStateOf(moneyExchange.scope.toString()) }
    var showScopeExchange by remember { mutableIntStateOf(moneyExchange.scope.getLabel()) }
    val descriptionValue = moneyExchange.description ?: ""
    var descriptionExchange by remember { mutableStateOf(descriptionValue) }

    val updateValueExchange: (String) -> Unit = { newValue ->
        val replace = newValue.replace(",", ".")
        valueExchange = replace
    }
    val updateTypeExchange: (String, Int) -> Unit = { newValue, newShow ->
        typeExchange = newValue
        showTypeExchange = newShow
    }
    val updateScopeExchange: (String, Int) -> Unit = { newValue, newShow ->
        scopeExchange = newValue
        showScopeExchange = newShow
    }
    val updateDescriptionExchange: (String) -> Unit = { newValue ->
        descriptionExchange = newValue
    }

    if (adviceTriggered) {
        adviceTriggered = false
        errorMessageList.clear()
        val validForm = isValidForm(valueExchange, typeExchange, scopeExchange)

        if (validForm) {
            val updatedMoneyExchange = MoneyExchange(
                valueExchange.toDouble(),
                typeExchange,
                scopeExchange,
                descriptionExchange
            )
            MoneyExchangeService.editMoneyExchange(monthAssociated, exchange, updatedMoneyExchange)
            adviceTriggered = false
            navController?.popBackStack()
        } else {
            isPopUpVisible = true
        }
    }

    if (isPopUpVisible) {
        InvalidFormPopUp(messageList = errorMessageList, onConfirmButton = hidePopUp)
    }

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.REGISTERED_USER,
        triggerScreen = EDIT_EXCHANGE_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.ALL,
        onAdviceTriggered = { adviceTriggered = true }
    ) {
        Title(configuration = TitleConfiguration.ADD_EXCHANGE)

        Spacer(modifier = Modifier.padding(dimens.space1))
        NumberInput(valueInput = valueExchange, onValueChange = updateValueExchange)

        Spacer(modifier = Modifier.padding(dimens.space0))
        MenuInput(
            configuration = MenuInputConfiguration.TYPE,
            valueInput = showTypeExchange,
            onValueChange = updateTypeExchange
        )

        Spacer(modifier = Modifier.padding(dimens.space0))
        MenuInput(
            configuration = MenuInputConfiguration.SCOPE,
            valueInput = showScopeExchange,
            onValueChange = updateScopeExchange
        )

        Spacer(modifier = Modifier.padding(dimens.space0))
        BigTextInput(valueInput = descriptionExchange, onValueChange = updateDescriptionExchange)

    }

}

/**
 * Preview function for the EditExchangeScreen composable.
 */
@Preview(showBackground = true)
@Composable
fun EditExchangeScreenPreview() {
    AddExchangeScreen()
}
