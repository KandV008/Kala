package com.example.kala.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.R
import com.example.kala.ui.components.Logo
import com.example.kala.ui.components.LogoConfiguration
import com.example.kala.ui.components.buttons.LargeButton
import com.example.kala.ui.components.buttons.LargeButtonConfiguration
import com.example.kala.ui.screens.commons.FooterConfiguration
import com.example.kala.ui.screens.commons.HeaderConfiguration
import com.example.kala.ui.screens.commons.Layout
import com.example.kala.ui.screens.navigation.LOG_IN_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.MAIN_SCREEN_ROUTE
import com.example.kala.ui.screens.navigation.SIGN_UP_SCREEN_ROUTE
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
import com.example.kala.ui.theme.dimens

/**
 * Main screen composable that displays the main interface with options to log in or sign up.
 *
 * @param navController Optional navigation controller to handle navigation actions.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController? = null
) {
    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.UNREGISTERED_USER,
        triggerScreen = MAIN_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.EMPTY,
        onAdviceTriggered = {},
    ) {
        Logo(LogoConfiguration.LARGE)
        Spacer(modifier = Modifier.padding(dimens.space3))
        SubTitle()

        Spacer(modifier = Modifier.padding(dimens.space2))
        Spacer(modifier = Modifier.padding(dimens.space3))
        LargeButton(
            configuration = LargeButtonConfiguration.LOG_IN,
            onAdviceTriggered = {
                navController?.navigate(route = LOG_IN_SCREEN_ROUTE)
            }
        )

        Spacer(modifier = Modifier.padding(dimens.space3))
        LargeButton(
            configuration = LargeButtonConfiguration.SIGN_UP,
            onAdviceTriggered = {
                navController?.navigate(route = SIGN_UP_SCREEN_ROUTE)
            }
        )
    }
}

/**
 * Composable function to display the subtitle with a specific style.
 */
@Composable
fun SubTitle() {
    Box(
        modifier = Modifier
            .height(dimens.height2)
            .width(dimens.widthMessage)
            .border(dimens.border, Color.Black)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.slogan),
            fontSize = dimens.fontSize0,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Composable function for previewing the main screen.
 */
@Preview(
    name = SMALL_PHONE_NAME,
    widthDp = SMALL_PHONE_WIDTH_DP,
    heightDp = SMALL_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun MainScreenCompactSmallPreview() {
    AppUtils(appDimens = SmallPhone) {
        MainScreen()
    }
}

@Preview(
    name = MEDIUM_PHONE_NAME,
    widthDp = MEDIUM_PHONE_WIDTH_DP,
    heightDp = MEDIUM_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun MainScreenCompactMediumPreview() {
    AppUtils(appDimens = MediumPhone) {
        MainScreen()
    }
}

@Preview(
    name = LARGE_PHONE_NAME,
    widthDp = LARGE_PHONE_WIDTH_DP,
    heightDp = LARGE_PHONE_HEIGHT_DP,
    showBackground = true
)
@Composable
fun MainScreenCompactPreview() {
    AppUtils(appDimens = LargePhone) {
        MainScreen()
    }
}

@Preview(
    name = TABLET_NAME,
    widthDp = TABLET_WIDTH_DP,
    heightDp = TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun MainScreenMediumPreview() {
    AppUtils(appDimens = Tablet) {
        MainScreen()
    }
}

@Preview(
    name = LARGE_TABLET_NAME,
    widthDp = LARGE_TABLET_WIDTH_DP,
    heightDp = LARGE_TABLET_HEIGHT_DP,
    showBackground = true
)
@Composable
fun MainScreenExpandedPreview() {
    AppUtils(appDimens = LargeTablet) {
        MainScreen()
    }
}

