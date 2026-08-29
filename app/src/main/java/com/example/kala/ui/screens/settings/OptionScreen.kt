package com.example.kala.ui.screens.settings

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.R
import com.example.kala.model.FireBaseService
import com.example.kala.model.MonthInformationService
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.components.buttons.LargeButton
import com.example.kala.ui.components.buttons.LargeButtonConfiguration
import com.example.kala.ui.components.popUps.ConfirmationPopUp
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.LayoutWithNoScroll
import com.example.kala.ui.screens.navigation.MAIN_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.OPTION_SCREEN_ROUTE
import com.example.kala.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth.getInstance
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment

/**
 * List of configurations for the type buttons in the Option screen.
 */
val typeButtons: List<LargeButtonConfiguration> = listOf(
    LargeButtonConfiguration.LOG_OUT,
    LargeButtonConfiguration.DELETE_USER,
)

/**
 * Composable function for rendering the Option screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OptionScreen(navController: NavController? = null) {
    LayoutWithNoScroll(
        navController = navController,
        headerConfiguration = HeaderConfiguration.OPTION_SCREEN,
        triggerScreen = OPTION_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_HOME,
        onAdviceTriggered = { }
    ) {
        Title(configuration = TitleConfiguration.OPTIONS)
        Spacer(modifier = Modifier.padding(dimens.space3))
        OptionScreenBody(navController)
        Spacer(modifier = Modifier.padding(dimens.space3))
    }
}

/**
 * Composable function for rendering the body of the Option screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("ShowToast")
@Composable
fun OptionScreenBody(navController: NavController? = null) {
    var deleteButtonTriggered by remember {
        mutableStateOf(false)
    }
    var deletingUser by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val successMessage = stringResource(R.string.delete_account_success_message)
    val failedMessage = stringResource(R.string.delete_account_failed_message)

    val optionFunctions: List<() -> Unit> = listOf(
        {
            getInstance().signOut()
            MonthInformationService.clean()
            navController?.navigate(MAIN_SCREEN_ROUTE)
        },
        {
            deleteButtonTriggered = true
        }
    )

    if (deleteButtonTriggered) {
        ConfirmationPopUp(
            onConfirmButton = {
                deleteButtonTriggered = false
                deletingUser = true
            },
            onDismissButton = {
                deleteButtonTriggered = false
            }
        )
    }

    LaunchedEffect(deletingUser) {
        if (deletingUser) {
            FireBaseService.deleteUser(
                {
                    Toast.makeText(
                        context,
                        failedMessage,
                        Toast.LENGTH_LONG
                    ).show()

                    deletingUser = false
                }
            ) {
                Toast.makeText(
                    context,
                    successMessage,
                    Toast.LENGTH_LONG
                ).show()

                deletingUser = false
                navController?.navigate(MAIN_SCREEN_ROUTE)
            }
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(typeButtons) { index, type ->
            LargeButton(
                configuration = type,
                onAdviceTriggered = optionFunctions[index]
            )

            Spacer(
                modifier = Modifier.padding(dimens.space1)
            )
        }
    }
}


/**
 * Preview for the Option screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun OptionScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        OptionScreen()
    }
}

/**
 * Preview for the Option screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun OptionScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        OptionScreen()
    }
}

/**
 * Preview for the Option screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun OptionScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        OptionScreen()
    }
}

/**
 * Preview for the Option screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun OptionScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        OptionScreen()
    }
}

/**
 * Preview for the Option screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun OptionScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        OptionScreen()
    }
}