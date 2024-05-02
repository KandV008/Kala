package com.example.kala.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.LANGUAGE_SCREEN_ROUTE
import com.example.kala.configuration.SmallButtonConfiguration
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.screens.components.buttons.SmallButton
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
            LanguageScreenBody()
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.padding(dimens.space4))
        }
    }
}

/**
 * Composable function for rendering the body of the Language screen.
 */
@Composable
fun LanguageScreenBody(){
    Row {
        SmallButton(configuration = SmallButtonConfiguration.ENGLISH)
        Spacer(modifier = Modifier.padding(dimens.space2))
        SmallButton(configuration = SmallButtonConfiguration.SPANISH)
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
