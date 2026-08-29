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
import com.example.kala.ui.components.inputs.SmallTextInput
import com.example.kala.ui.components.inputs.SmallTextInputConfiguration
import com.example.kala.ui.components.popUps.InvalidFormPopUp
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.SIGN_UP_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.FormValidation.isValidSignUp
import com.example.kala.ui.screens.utilities.errorMessageList
import com.example.kala.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth
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

private val FAILURE_CREATING_USER_MESSAGE = R.string.failure_creating_user_message

/**
 * Composable function for rendering the SignUp screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
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

        if (validForm) {
            FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(emailValue, passwordValue)
                .addOnSuccessListener {
                    FirebaseAuth.getInstance().currentUser?.uid?.let {
                        FireBaseService.saveUser(it)
                        navController?.navigate(route = HOME_SCREEN_ROUTE)
                    }
                }
                .addOnFailureListener {
                    errorMessageList.add(FAILURE_CREATING_USER_MESSAGE)
                    isPopUpVisible = true
                }
        } else {
            isPopUpVisible = true
        }
    }

    if (isPopUpVisible) {
        InvalidFormPopUp(messageList = errorMessageList, onConfirmButton = hidePopUp)
    }

    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.UNREGISTERED_USER,
        triggerScreen = SIGN_UP_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_NEXT,
        onAdviceTriggered = { adviceTriggered = true }
    ) {
        Logo(LogoConfiguration.SMALL)
        Spacer(modifier = Modifier.padding(dimens.space1))
        Title(configuration = TitleConfiguration.SIGN_UP)

        Spacer(modifier = Modifier.padding(dimens.space1))
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
    }
}



/**
 * Preview for the Sign Up screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun SignUpScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        SignUpScreen()
    }
}

/**
 * Preview for the Sign Up screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun SignUpScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        SignUpScreen()
    }
}

/**
 * Preview for the Sign Up screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun SignUpScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        SignUpScreen()
    }
}

/**
 * Preview for the Sign Up screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun SignUpScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        SignUpScreen()
    }
}

/**
 * Preview for the Sign Up screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun SignUpScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        SignUpScreen()
    }
}