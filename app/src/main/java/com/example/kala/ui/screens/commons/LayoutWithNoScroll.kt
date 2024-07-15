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

@Composable
fun LayoutWithNoScroll(
    navController: NavController?,
    headerConfiguration: HeaderConfiguration,
    triggerScreen: String,
    footerConfiguration: FooterConfiguration,
    onAdviceTriggered: () -> Unit,
    function: @Composable () -> Unit
){
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
                .background(BoneWhite)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(dimens.space1))
            function()
        }
    }
}