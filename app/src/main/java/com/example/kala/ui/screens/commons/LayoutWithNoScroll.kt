package com.example.kala.ui.screens.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering a layout without scrolling, wrapping the content in a Scaffold.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param headerConfiguration The configuration for the top header bar.
 * @param triggerScreen The identifier for the current screen.
 * @param footerConfiguration The configuration for the bottom footer bar.
 * @param onAdviceTriggered Callback function triggered when advice is triggered.
 * @param function The composable function to be included as the main content of the layout.
 */
@Composable
fun LayoutWithNoScroll(
    navController: NavController?,
    headerConfiguration: HeaderConfiguration,
    triggerScreen: String,
    footerConfiguration: FooterConfiguration,
    onAdviceTriggered: () -> Unit,
    function: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            Header(
                configuration = headerConfiguration,
                navController = navController,
                triggerScreen = triggerScreen,
            )
        },
        bottomBar = {
            Footer(
                configuration = footerConfiguration,
                navController = navController,
                onAdviceTriggered = onAdviceTriggered
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(dimens.space1))
            function()
        }
    }
}