package com.example.kala.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.ui.screens.navigation.ADD_EXCHANGE_SCREEN_ROUTE
import com.example.kala.ui.components.FooterConfiguration
import com.example.kala.ui.components.HeaderConfiguration
import com.example.kala.ui.screens.navigation.LOG_IN_SCREEN_ROUTE
import com.example.kala.ui.components.buttons.LargeButtonConfiguration
import com.example.kala.ui.components.LogoConfiguration
import com.example.kala.ui.screens.navigation.SIGN_UP_SCREEN_ROUTE
import com.example.kala.ui.screens.commons.Footer
import com.example.kala.ui.screens.commons.Header
import com.example.kala.ui.components.Logo
import com.example.kala.ui.components.buttons.LargeButton
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController? = null
) {
    var logInButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onLogInTriggered = {
        logInButtonTriggered = true
    }
    if (logInButtonTriggered){
        logInButtonTriggered = false
        navController?.navigate(route = LOG_IN_SCREEN_ROUTE)
    }

    var signUpButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onSignUpTriggered = {
        signUpButtonTriggered = true
    }
    if (signUpButtonTriggered){
        signUpButtonTriggered = false
        navController?.navigate(route = SIGN_UP_SCREEN_ROUTE)
    }

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
            LargeButton(
                configuration = LargeButtonConfiguration.LOG_IN,
                onAdviceTriggered = onLogInTriggered
            )

            Spacer(modifier = Modifier.padding(dimens.space3))
            LargeButton(
                configuration = LargeButtonConfiguration.SIGN_UP,
                onAdviceTriggered = onSignUpTriggered
            )

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

