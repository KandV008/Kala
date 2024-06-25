package com.example.kala.ui.screens.authentication

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.R
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
import com.example.kala.ui.screens.navigation.MAIN_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.errorMessageList
import com.example.kala.ui.theme.dimens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

private val FAILURE_LOG_IN_MESSAGE = R.string.failure_log_in_message //TODO Adapt message

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(
    navController: NavController? = null
) {

    var isPopUpVisible by remember { mutableStateOf(false) }
    val hidePopUp: () -> Unit = {
        isPopUpVisible = false
    }

    var adviceTriggered by remember { mutableStateOf(false) }
    var emailValue by remember { mutableStateOf("") }

    val updateEmailValue: (String) -> Unit = { newValue ->
        emailValue = newValue
    }


    if (adviceTriggered) {
        adviceTriggered = false
        errorMessageList.clear()
        val current = LocalContext.current
        Firebase
            .auth
            .sendPasswordResetEmail(emailValue)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    Toast
                        .makeText(current, "Request sent. Check your email", Toast.LENGTH_LONG) // TODO Translate
                        .show()
                    navController?.navigate(route = MAIN_SCREEN_ROUTE)
                } else {
                    errorMessageList.add(FAILURE_LOG_IN_MESSAGE)
                    isPopUpVisible = true
                }
            }
    }

    if (isPopUpVisible) {
        InvalidFormPopUp(messageList = errorMessageList, onConfirmButton = hidePopUp)
    }

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.UNREGISTERED_USER,
        triggerScreen = ADD_EXCHANGE_SCREEN_ROUTE, // TODO Create Recovery Password Help Screen
        footerConfiguration = FooterConfiguration.ONLY_BACK,
        onAdviceTriggered = { adviceTriggered = true }
    ) {
        Logo(LogoConfiguration.SMALL)
        Spacer(modifier = Modifier.padding(dimens.space1))
        Title(configuration = TitleConfiguration.RECOVERY_PASS)

        Spacer(modifier = Modifier.padding(dimens.space3))
        Spacer(modifier = Modifier.padding(dimens.space3))
        SmallTextInput(
            configuration = SmallTextInputConfiguration.EMAIL,
            emailValue,
            updateEmailValue
        )

        Spacer(modifier = Modifier.padding(dimens.space3))

        LargeButton(configuration = LargeButtonConfiguration.SEND_REQUEST) {
            adviceTriggered = true
        }

    }
}

/**
 * Composable function for previewing adding a new money exchange.
 *
 */
@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen()
}