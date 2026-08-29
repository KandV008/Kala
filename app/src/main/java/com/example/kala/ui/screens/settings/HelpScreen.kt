package com.example.kala.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.ui.components.Title
import com.example.kala.ui.components.TitleConfiguration
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.HELP_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.HOME_SCREEN_ROUTE
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
 * Composable function for rendering the Help screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HelpScreen(
    navController: NavController? = null,
    triggerScreen: String,
) {
    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.HELP_SCREEN,
        triggerScreen = HELP_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.ONLY_BACK,
        onAdviceTriggered = { }
    ) {
        Title(configuration = TitleConfiguration.HELP)
        Spacer(modifier = Modifier.padding(dimens.space3))
        HelpScreenBody(HelpService.getTextAdviceScreen(triggerScreen))
        Spacer(modifier = Modifier.padding(dimens.space3))
    }
}

/**
 * Composable function for rendering the body of the Help screen.
 *
 * @param advices The list of advices to display in the Help screen.
 */
@Composable
fun HelpScreenBody(advices: List<Int>) {
    LazyColumn(
        modifier = Modifier
            .width(dimens.widthMessage)
            .height(dimens.heightHelp)
            .background(Color.White)
            .border(dimens.border, Color.Black)
            .padding(dimens.padding5),
    ) {
        items(advices) { advice ->
            Text(
                text = stringResource(id = advice),
                color = Color.Black,
                fontSize = dimens.fontSize0
            )
            Spacer(modifier = Modifier.padding(dimens.space1))
        }
    }
}

/**
 * Composable function for previewing the Help screen on a small phone.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HelpScreenSmallPhonePreview() {
    AppUtils(appDimens = SmallPhone) {
        HelpScreen(triggerScreen = HOME_SCREEN_ROUTE)
    }
}

/**
 * Composable function for previewing the Help screen on a medium phone.
 */
@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HelpScreenMediumPhonePreview() {
    AppUtils(appDimens = MediumPhone) {
        HelpScreen(triggerScreen = HOME_SCREEN_ROUTE)
    }
}

/**
 * Composable function for previewing the Help screen on a large phone.
 */
@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HelpScreenLargePhonePreview() {
    AppUtils(appDimens = LargePhone) {
        HelpScreen(triggerScreen = HOME_SCREEN_ROUTE)
    }
}

/**
 * Composable function for previewing the Help screen on a tablet.
 */
@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HelpScreenTabletPreview() {
    AppUtils(appDimens = Tablet) {
        HelpScreen(triggerScreen = HOME_SCREEN_ROUTE)
    }
}

/**
 * Composable function for previewing the Help screen on a large tablet.
 */
@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun HelpScreenLargeTabletPreview() {
    AppUtils(appDimens = LargeTablet) {
        HelpScreen(triggerScreen = HOME_SCREEN_ROUTE)
    }
}
