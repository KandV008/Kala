package com.example.kala.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HOME_SCREEN_ROUTE
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.model.HelpService
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

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
){
    Scaffold(
        topBar = {
            Header(configuration = HeaderConfiguration.HELP_SCREEN, navController)
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
            Title(configuration = TitleConfiguration.HELP)
            Spacer(modifier = Modifier.weight(0.7F))
            HelpScreenBody(HelpService.getTextAdviceScreen(triggerScreen))
            Spacer(modifier = Modifier.weight(0.7F))
            Spacer(modifier = Modifier.padding(dimens.space4))
        }
    }
}

/**
 * Composable function for rendering the body of the Help screen.
 *
 * @param advices The list of advices to display in the Help screen.
 */
@Composable
fun HelpScreenBody(advices: List<String>){
    LazyColumn(
        modifier = Modifier
            .width(dimens.width7)
            .height(dimens.height10)
            .background(Color.White)
            .border(dimens.border, Color.Black)
            .padding(dimens.padding5),
    ){
        items(advices){
                advice ->
            Text(
                text = advice,
                color = Color.Black,
                fontSize = dimens.fontSize0
            )
            Spacer(modifier = Modifier.padding(dimens.space1))
        }
    }
}

/**
 * Composable function for previewing the Help screen.
 * This preview function is used for testing and visualizing the Help screen.
 */
@Preview(showBackground = true)
@Composable
fun HelpScreenPreview(){
    HelpScreen(triggerScreen = HOME_SCREEN_ROUTE)
}
