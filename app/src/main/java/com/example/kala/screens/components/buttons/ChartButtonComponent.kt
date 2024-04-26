package com.example.kala.screens.components.buttons

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.kala.configuration.ChartButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function to display a chart button.
 *
 * @param alpha The alpha value for the button. Default is 1F (fully opaque).
 * @param configuration The configuration for the chart button, containing SVG file information.
 * @param onAdviceTriggered Callback to be invoked when the button is clicked.
 */
@Composable
fun ChartButton(
    alpha: Float = 1F,
    configuration: ChartButtonConfiguration,
    onAdviceTriggered: () -> Unit = {},
){
    Box(
        modifier = Modifier
            .alpha(alpha)
            .size(50.dp)
            .shadow(10.dp, shape = CircleShape)
            .border(2.dp, Color.Black, CircleShape)
            .background(Color.White)
    ) {
        Button(
            onClick = onAdviceTriggered,
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = CircleShape,
            contentPadding = PaddingValues(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp)
            ,
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

/**
 * Composable function for rendering a preview of the ChartButton component.
 * This preview function is used for testing and visualizing the ChartButton component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ChartButtonPreview() {
    Scaffold {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            items(ChartButtonConfiguration.entries.toTypedArray()){
                    value ->
                ChartButton(configuration = value)
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}
