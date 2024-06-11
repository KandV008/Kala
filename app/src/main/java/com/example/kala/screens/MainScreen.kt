package com.example.kala.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.configuration.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.LargeButtonConfiguration
import com.example.kala.configuration.LogoConfiguration
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Logo
import com.example.kala.screens.components.buttons.LargeButton
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController? = null
) {
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
                configuration = FooterConfiguration.EMPTY,
                navController = navController,
                onAdviceTriggered = { }
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
            Logo(LogoConfiguration.LARGE)
            Spacer(modifier = Modifier.padding(dimens.space3))
            SubTitle()

            Spacer(modifier = Modifier.padding(dimens.space2))
            Spacer(modifier = Modifier.padding(dimens.space3))
            LargeButton(configuration = LargeButtonConfiguration.LOG_IN)

            Spacer(modifier = Modifier.padding(dimens.space3))
            LargeButton(configuration = LargeButtonConfiguration.SIGN_UP)

            Spacer(modifier = Modifier.padding(dimens.space3))
        }
    }
}

@Composable
fun SubTitle(){
    Box(
        modifier = Modifier
            .height(dimens.height2)
            .width(dimens.width7)
            .border(dimens.border, Color.Black)
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Your income and expense management app",
            fontSize = dimens.fontSize0,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Composable function for previewing adding a new money exchange.
 */
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

