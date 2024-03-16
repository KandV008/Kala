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
import com.example.kala.screens.components.Chart
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.buttons.LargeButton
import com.example.kala.screens.components.buttons.MediumButton
import com.example.kala.ui.theme.BoneWhite

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

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreenBody(
    navController: NavController? = null
){
    LargeButton(
        configuration = LargeButtonConfiguration.ADD_EXCHANGE,
        navController = navController
    )
    Spacer(modifier = Modifier.padding(10.dp))
    Row {
        MediumButton(configuration = MediumButtonConfiguration.SEE_REPORT)
        Spacer(modifier = Modifier.padding(10.dp))
        MediumButton(configuration = MediumButtonConfiguration.SEE_RECORD)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}
