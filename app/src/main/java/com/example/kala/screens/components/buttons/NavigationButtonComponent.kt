package com.example.kala.screens.components.buttons

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kala.configuration.DEFAULT_FLOAT
import com.example.kala.configuration.NavigationButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationButton(
    configuration: NavigationButtonConfiguration,
    alpha: Float = DEFAULT_FLOAT,
    navController: NavController? = null,
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .alpha(alpha)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
            ){
        Button(
            onClick = {
                if (NavigationButtonConfiguration.BACK == configuration){
                    navController?.popBackStack()
                    return@Button
                }
                navController?.navigate(route = configuration.getRoute())
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(60.dp)
                .alpha(alpha),
            contentPadding = PaddingValues(10.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Image(
                painter = painterResource(
                    id = configuration.getSVGFile()
                ),
                contentDescription = SVG_DESCRIPTION
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun NavigationButtonPreview() {
    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            NavigationButton(NavigationButtonConfiguration.LANGUAGE)
            Spacer(modifier = Modifier.padding(5.dp))
            NavigationButton(NavigationButtonConfiguration.HOME)
            Spacer(modifier = Modifier.padding(5.dp))
            NavigationButton(NavigationButtonConfiguration.HELP)
            Spacer(modifier = Modifier.padding(5.dp))
            NavigationButton(NavigationButtonConfiguration.BACK)
            Spacer(modifier = Modifier.padding(5.dp))
            NavigationButton(NavigationButtonConfiguration.NEXT)
            Spacer(modifier = Modifier.padding(5.dp))
            NavigationButton(NavigationButtonConfiguration.OPTIONS)
        }
    }
}