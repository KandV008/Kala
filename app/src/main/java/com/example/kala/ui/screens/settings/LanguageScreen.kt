package com.example.kala.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
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
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.components.buttons.NavigationButtonConfiguration
import com.example.kala.ui.components.buttons.SmallButton
import com.example.kala.ui.components.buttons.SmallButtonConfiguration
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.LANGUAGE_SCREEN_ROUTE
import com.example.kala.ui.screens.utilities.Utilities.SetLocale
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
 * Composable function for rendering the Language screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LanguageScreen(navController: NavController? = null) {
    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.LANGUAGE_SCREEN,
        triggerScreen = LANGUAGE_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.ONLY_BACK,
        onAdviceTriggered = { }
    ) {
        Title(configuration = TitleConfiguration.LANGUAGES)
        Spacer(modifier = Modifier.padding(dimens.space3))
        LanguageScreenBody(navController)
        Spacer(modifier = Modifier.padding(dimens.space3))
    }
}

/**
 * Composable function for rendering the body of the Language screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@Composable
fun LanguageScreenBody(navController: NavController?) {

    var action by remember { mutableStateOf("") }
    var adviceTriggered by remember { mutableStateOf(false) }
    val onAdviceTriggered: (String) -> Unit = {
        action = it
        adviceTriggered = true
    }

    if (adviceTriggered) {
        adviceTriggered = false
        SetLocale(action)
        NavigationButtonConfiguration.LANGUAGE.updateIcon(action)
        navController?.popBackStack()
    }

    Row {
        SmallButton(configuration = SmallButtonConfiguration.ENGLISH, onAdviceTriggered)
        Spacer(modifier = Modifier.padding(dimens.space2))
        SmallButton(configuration = SmallButtonConfiguration.SPANISH, onAdviceTriggered)
    }
}

/**
 * Preview for the Language screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun LanguageScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        LanguageScreen()
    }
}

/**
 * Preview for the Language screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun LanguageScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        LanguageScreen()
    }
}

/**
 * Preview for the Language screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun LanguageScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        LanguageScreen()
    }
}

/**
 * Preview for the Language screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun LanguageScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        LanguageScreen()
    }
}

/**
 * Preview for the Language screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun LanguageScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        LanguageScreen()
    }
}
