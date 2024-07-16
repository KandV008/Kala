package com.example.kala.ui.screens.authentication

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.R
import com.example.kala.model.FireBaseService
import com.example.kala.ui.components.Logo
import com.example.kala.ui.components.LogoConfiguration
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.components.buttons.LargeButton
import com.example.kala.ui.components.buttons.LargeButtonConfiguration
import com.example.kala.ui.components.inputs.SmallTextInput
import com.example.kala.ui.components.inputs.SmallTextInputConfiguration
import com.example.kala.ui.components.popUps.InvalidFormPopUp
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.CHANGE_PASS_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.LOG_IN_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.FormValidation.isValidSignUp
import com.example.kala.ui.screens.utilities.errorMessageList
import com.example.kala.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth

private val FAILURE_LOG_IN_MESSAGE = R.string.failure_log_in_message

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogInScreen(
    navController: NavController? = null
) {

    var isPopUpVisible by remember { mutableStateOf(false) }
    val hidePopUp: () -> Unit = {
        isPopUpVisible = false
    }

    var adviceTriggered by remember { mutableStateOf(false) }

    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    val updateEmailValue: (String) -> Unit = { newValue ->
        emailValue = newValue
    }
    val updatePasswordValue: (String) -> Unit = { newValue ->
        passwordValue = newValue
    }

    if (adviceTriggered) {
        adviceTriggered = false
        errorMessageList.clear()
        val validForm = isValidSignUp(emailValue, passwordValue)

        if (validForm){
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(emailValue, passwordValue)
                .addOnSuccessListener {
                    FireBaseService.loadUser(emailValue){
                        navController?.navigate(route = HOME_SCREEN_ROUTE)
                    }
                }
                .addOnFailureListener{
                    errorMessageList.add(FAILURE_LOG_IN_MESSAGE)
                    isPopUpVisible = true
                }
        } else {
            isPopUpVisible = true
        }
    }

    if(isPopUpVisible){
        InvalidFormPopUp(messageList = errorMessageList, onConfirmButton = hidePopUp)
    }

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.UNREGISTERED_USER,
        triggerScreen = LOG_IN_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_NEXT,
        onAdviceTriggered = { adviceTriggered = true }
    ) {
        Logo(LogoConfiguration.SMALL)
        Spacer(modifier = Modifier.padding(dimens.space1))
        Title(configuration = TitleConfiguration.LOG_IN)

        Spacer(modifier = Modifier.padding(dimens.space1))
        SmallTextInput(
            configuration = SmallTextInputConfiguration.EMAIL,
            emailValue,
            updateEmailValue
        )

        Spacer(modifier = Modifier.padding(dimens.space0))
        SmallTextInput(
            configuration = SmallTextInputConfiguration.PASSWORD,
            passwordValue,
            updatePasswordValue
        )

        Spacer(modifier = Modifier.padding(dimens.space2))
        LargeButton(configuration = LargeButtonConfiguration.FORGOT_PASS) {
            navController?.navigate(route = CHANGE_PASS_SCREEN_ROUTE)
        }

        Spacer(modifier = Modifier.padding(dimens.space0))
        // TODO Add special log in
    }
}

/**
 * Composable function for previewing adding a new money exchange.
 *
 */
@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    LogInScreen()
}