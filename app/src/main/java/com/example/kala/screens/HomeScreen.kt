package com.example.kala.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kala.configuration.ChartConfiguration
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.LargeButtonConfiguration
import com.example.kala.configuration.MediumButtonConfiguration
import com.example.kala.configuration.RECORD_SCREEN_ROUTE
import com.example.kala.screens.components.Chart
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.buttons.LargeButton
import com.example.kala.screens.components.buttons.MediumButton
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for rendering the Home screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController? = null
){
    Scaffold(
        topBar = {
            Header(configuration = HeaderConfiguration.REGISTERED_USER, navController)
        },
        bottomBar = {
            Footer(configuration = FooterConfiguration.EMPTY, navController)
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(50.dp))
            Chart(configuration = ChartConfiguration.HOME_PAGE)
            Spacer(modifier = Modifier.weight(0.5f))
            HomeScreenBody(navController)
            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}

/**
 * Composable function for rendering the body of the Home screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreenBody(
    navController: NavController? = null
){
    var leftButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onLeftTriggered = {
        leftButtonTriggered = true
    }
    var rightButtonTriggered by remember {
        mutableStateOf(false)
    }
    val onRightTriggered = {
        rightButtonTriggered = true
    }

    if (rightButtonTriggered){
        navController?.navigate(route = RECORD_SCREEN_ROUTE)
    }

    if (leftButtonTriggered){
        // TODO Report Screen
    }

    LargeButton(
        configuration = LargeButtonConfiguration.ADD_EXCHANGE,
        navController = navController
    )
    Spacer(modifier = Modifier.padding(10.dp))
    Row {
        MediumButton(configuration = MediumButtonConfiguration.SEE_REPORT, onLeftTriggered)
        Spacer(modifier = Modifier.padding(10.dp))
        MediumButton(configuration = MediumButtonConfiguration.SEE_RECORD, onRightTriggered)
    }
}

/**
 * Composable function for previewing the Home screen.
 * This preview function is used for testing and visualizing the Home screen.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}

