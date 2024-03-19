package com.example.kala.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.ui.theme.BoneWhite

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutExchangeScreen(
    navController: NavController? = null,
    monthAssociated: String,
    exchange: Int,
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
            Spacer(modifier = Modifier.padding(50.dp))
            Title(configuration = TitleConfiguration.MORE_INFO)



            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun AboutExchangeScreenPreview(){
    AboutExchangeScreen(monthAssociated =  "", exchange = 1)
}