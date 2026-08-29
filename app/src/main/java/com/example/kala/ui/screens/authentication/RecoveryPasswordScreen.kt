package com.example.kala.ui.screens.authentication

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.kala.ui.screens.navigation.CHANGE_PASS_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.MAIN_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.FormValidation.isValidRecoveryPassword
import com.example.kala.ui.screens.utilities.errorMessageList
import com.example.kala.ui.theme.dimens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
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

private val FAILURE_SEND_REQUEST_MESSAGE = R.string.failure_send_request_message

/**
 * Composable function for rendering the Change Password screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(
    navController: NavController? = null
) {
    val requestMessage = stringResource(id = R.string.request_message)

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
        val validForm = isValidRecoveryPassword(emailValue)

        if (validForm) {
            Firebase
                .auth
                .sendPasswordResetEmail(emailValue)
                .addOnSuccessListener {
                    Toast
                        .makeText(current, requestMessage, Toast.LENGTH_LONG)
                        .show()
                    navController?.navigate(route = MAIN_SCREEN_ROUTE)
                }
                .addOnFailureListener{
                    errorMessageList.add(FAILURE_SEND_REQUEST_MESSAGE)
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
        triggerScreen = CHANGE_PASS_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.ONLY_BACK,
        onAdviceTriggered = { adviceTriggered = true }
    ) {
        Logo(LogoConfiguration.SMALL)
        Spacer(modifier = Modifier.padding(dimens.space1))
        Title(configuration = TitleConfiguration.RECOVERY_PASS)

        Spacer(modifier = Modifier.padding(dimens.space3))
        RecoveryPassMessage()
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

@Composable
fun RecoveryPassMessage() {
    Box(
        modifier = Modifier
            .height(dimens.height2)
            .width(dimens.widthMessage)
            .border(dimens.border, Color.Black)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.recovery_pass_msg),
            fontSize = dimens.fontSize0,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Preview for the Change Password screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ChangePasswordScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        ChangePasswordScreen()
    }
}

/**
 * Preview for the Change Password screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ChangePasswordScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        ChangePasswordScreen()
    }
}

/**
 * Preview for the Change Password screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ChangePasswordScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        ChangePasswordScreen()
    }
}

/**
 * Preview for the Change Password screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ChangePasswordScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        ChangePasswordScreen()
    }
}

/**
 * Preview for the Change Password screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun ChangePasswordScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        ChangePasswordScreen()
    }
}