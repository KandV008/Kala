package com.example.kala.screens.utilities

import android.annotation.SuppressLint
import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.example.kala.components.FooterConfiguration
import com.example.kala.components.HeaderConfiguration
import com.example.kala.navigation.LANGUAGE_SCREEN_ROUTE
import com.example.kala.components.buttons.SmallButtonConfiguration
import com.example.kala.components.TitleConfiguration
import com.example.kala.components.Footer
import com.example.kala.components.Header
import com.example.kala.components.Title
import com.example.kala.components.buttons.SmallButton
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering the Language screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LanguageScreen(navController: NavController? = null){
    Scaffold(
        topBar = {
            Header(
                configuration = HeaderConfiguration.LANGUAGE_SCREEN,
                navController = navController,
                triggerScreen = LANGUAGE_SCREEN_ROUTE,
            )
        },
        bottomBar = {
            Footer(configuration = FooterConfiguration.BACK_AND_HOME, navController)
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(dimens.space4))
            Title(configuration = TitleConfiguration.LANGUAGES)
            Spacer(modifier = Modifier.weight(1f))
            LanguageScreenBody(navController)
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(dimens.space4))
        }
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
        navController?.popBackStack()
    }

    Row {
        SmallButton(configuration = SmallButtonConfiguration.ENGLISH, onAdviceTriggered)
        Spacer(modifier = Modifier.padding(dimens.space2))
        SmallButton(configuration = SmallButtonConfiguration.SPANISH, onAdviceTriggered)
    }
}

@Composable
fun SetLocale(action: String) {
    val context = LocalContext.current

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        context.getSystemService(LocaleManager::class.java)
            .applicationLocales = LocaleList.forLanguageTags(action)
    } else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(action))
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
