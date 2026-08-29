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
import com.example.kala.R
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
import com.example.kala.ui.screens.navigation.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.FormValidation.isValidForm
import com.example.kala.ui.screens.utilities.errorMessageList
import com.example.kala.ui.theme.dimens
import com.example.kala.ui.screens.utilities.LARGE_PHONE_HEIGHT_DP
import com.example.kala.ui.screens.utilities.LARGE_PHONE_NAME
import com.example.kala.ui.screens.utilities.LARGE_PHONE_WIDTH_DP
import com.example.kala.ui.screens.utilities.LARGE_TABLET_HEIGHT_DP
import com.example.kala.ui.screens.utilities.LARGE_TABLET_NAME
import com.example.kala.ui.screens.utilities.LARGE_TABLET_WIDTH_DP
import com.example.kala.ui.screens.utilities.MEDIUM_PHONE_HEIGHT_DP
import com.example.kala.ui.screens.utilities.MEDIUM_PHONE_NAME
import com.example.kala.ui.screens.utilities.MEDIUM_PHONE_WIDTH_DP
import com.example.kala.ui.screens.utilities.SMALL_PHONE_HEIGHT_DP
import com.example.kala.ui.screens.utilities.SMALL_PHONE_NAME
import com.example.kala.ui.screens.utilities.SMALL_PHONE_WIDTH_DP
import com.example.kala.ui.screens.utilities.TABLET_HEIGHT_DP
import com.example.kala.ui.screens.utilities.TABLET_NAME
import com.example.kala.ui.screens.utilities.TABLET_WIDTH_DP
import com.example.kala.ui.theme.AppUtils
import com.example.kala.ui.theme.LargePhone
import com.example.kala.ui.theme.LargeTablet
import com.example.kala.ui.theme.MediumPhone
import com.example.kala.ui.theme.SmallPhone
import com.example.kala.ui.theme.Tablet

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
    var showTypeExchange by remember { mutableIntStateOf(R.string.empty) }
    var scopeExchange by remember { mutableStateOf("") }
    var showScopeExchange by remember { mutableIntStateOf(R.string.empty) }
    var descriptionExchange by remember { mutableStateOf("") }

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

    if (isPopUpVisible) {
        InvalidFormPopUp(messageList = errorMessageList, onConfirmButton = hidePopUp)
    }

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.REGISTERED_USER,
        triggerScreen = ADD_EXCHANGE_SCREEN_ROUTE,
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
 * Preview for the Add Exchange screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AddExchangeScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        AddExchangeScreen()
    }
}

/**
 * Preview for the Add Exchange screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AddExchangeScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        AddExchangeScreen()
    }
}

/**
 * Preview for the Add Exchange screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AddExchangeScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        AddExchangeScreen()
    }
}

/**
 * Preview for the Add Exchange screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AddExchangeScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        AddExchangeScreen()
    }
}

/**
 * Preview for the Add Exchange screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun AddExchangeScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        AddExchangeScreen()
    }
}