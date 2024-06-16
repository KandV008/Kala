package com.example.kala.screens.authentication

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
import com.example.kala.R
import com.example.kala.configuration.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.LogoConfiguration
import com.example.kala.configuration.SmallTextInputConfiguration
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.model.FireBaseService
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Logo
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.inputs.SmallTextInput
import com.example.kala.screens.components.popups.InvalidFormPopUp
import com.example.kala.screens.errorMessageList
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth

private val INVALID_EMAIL_ERROR_MESSAGE = R.string.invalid_email_error_message
private val INVALID_PASSWORD_ERROR_MESSAGE = R.string.invalid_password_error_message
private val FAILURE_CREATING_USER_MESSAGE = R.string.failure_creating_user_message

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    navController: NavController? = null
) {

    var isPopUpVisible by remember { mutableStateOf(false) }
    val hidePopUp: () -> Unit = {
        isPopUpVisible = false
    }

    var adviceTriggered by remember { mutableStateOf(false) }

    var usernameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    val updateUsernameValue: (String) -> Unit = { newValue ->
        val replace = newValue.replace(",", ".")
        usernameValue = replace
    }
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
                .createUserWithEmailAndPassword(emailValue, passwordValue)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        FireBaseService.saveUser(emailValue)
                        navController?.navigate(route = HOME_SCREEN_ROUTE)
                    } else {
                        errorMessageList.add(FAILURE_CREATING_USER_MESSAGE)
                        isPopUpVisible = true
                    }
                }
        } else {
            isPopUpVisible = true
        }
    }

    if(isPopUpVisible){
        InvalidFormPopUp(messageList = errorMessageList, onConfirmButton = hidePopUp)
    }

    Scaffold(
        topBar = {
            Header(
                configuration = HeaderConfiguration.UNREGISTERED_USER,
                navController = navController,
                triggerScreen = ADD_EXCHANGE_SCREEN_ROUTE,
            )
        },
        bottomBar = {
            Footer(
                configuration = FooterConfiguration.BACK_AND_NEXT,
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
            Title(configuration = TitleConfiguration.SIGN_UP)

            Spacer(modifier = Modifier.padding(dimens.space1))
            SmallTextInput(
                configuration = SmallTextInputConfiguration.USERNAME,
                usernameValue,
                updateUsernameValue
            )

            Spacer(modifier = Modifier.padding(dimens.space0))
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

            Spacer(modifier = Modifier.padding(dimens.space0))
            // TODO Add special sign up

            Spacer(modifier = Modifier.padding(dimens.space4))
        }
    }
}



/**
 * Checks if the provided values for exchange are valid.
 *
 * @return `true` if all values are valid, `false` otherwise.
 */
fun isValidSignUp(
    email: String,
    password: String
): Boolean {
    if (isNotValidEmail(email)){
        errorMessageList.add(INVALID_EMAIL_ERROR_MESSAGE)
    }

    if (isNotValidPassword(password)){
        errorMessageList.add(INVALID_PASSWORD_ERROR_MESSAGE)
    }

    return errorMessageList.isEmpty()
}

fun isNotValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
    return !emailRegex.toRegex().matches(email)
}

fun isNotValidPassword(password: String): Boolean{
    val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"
    return !passwordRegex.toRegex().matches(password)
}

/**
 * Composable function for previewing adding a new money exchange.
 *
 */
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}