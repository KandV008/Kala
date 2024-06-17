package com.example.kala.screens.authentication

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.R
import com.example.kala.configuration.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.LargeButtonConfiguration
import com.example.kala.configuration.LogoConfiguration
import com.example.kala.configuration.MAIN_SCREEN_ROUTE
import com.example.kala.configuration.SmallTextInputConfiguration
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Logo
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.buttons.LargeButton
import com.example.kala.screens.components.inputs.SmallTextInput
import com.example.kala.screens.components.popups.InvalidFormPopUp
import com.example.kala.screens.errorMessageList
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

private val FAILURE_LOG_IN_MESSAGE = R.string.failure_log_in_message
private val INVALID_PASSWORD_ERROR_MESSAGE = R.string.invalid_password_error_message
private val DISTINCT_PASSWORD_ERROR_MESSAGE = R.string.distinct_password_error_message

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
                        .makeText(current, "Request sent. Check your email", Toast.LENGTH_LONG)
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

    Scaffold(
        topBar = {
            Header(
                configuration = HeaderConfiguration.UNREGISTERED_USER,
                navController = navController,
                triggerScreen = ADD_EXCHANGE_SCREEN_ROUTE, //TODO Create Help Page
            )
        },
        bottomBar = {
            Footer(
                configuration = FooterConfiguration.ONLY_BACK,
                navController = navController,
                onAdviceTriggered = { adviceTriggered = true }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(dimens.space4))
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
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen()
}