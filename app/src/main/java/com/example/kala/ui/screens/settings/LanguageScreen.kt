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

/**
 * Composable function for rendering the Language screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LanguageScreen(navController: NavController? = null){
    Layout(
        navController = navController,
        headerConfiguration = HeaderConfiguration.LANGUAGE_SCREEN,
        triggerScreen = LANGUAGE_SCREEN_ROUTE,
        footerConfiguration = FooterConfiguration.BACK_AND_HOME,
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
 */
@Composable
fun LanguageScreenBody(navController: NavController?){

    var action by remember { mutableStateOf("") }
    var adviceTriggered by remember { mutableStateOf(false) }
    val onAdviceTriggered: (String) -> Unit = {
        action = it
        adviceTriggered = true
    }

    if (adviceTriggered){
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
 * Preview function for testing and visualizing the Language screen.
 */
@Preview(showBackground = true)
@Composable
fun LanguageScreenPreview(){
    LanguageScreen()
}
