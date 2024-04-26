package com.example.kala.screens.components.buttons

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.configuration.SmallButtonConfiguration
import com.example.kala.configuration.invalidArgument
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for rendering a small-sized button with customizable configuration.
 *
 * @param configuration The configuration for the small-sized button.
 */
@Composable
fun SmallButton(configuration: SmallButtonConfiguration) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Button(
            onClick = {
                /* TODO: Implement onClick behavior */
                invalidArgument()
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp)),
            contentPadding = PaddingValues(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Black, shape = CircleShape)
                    .padding(8.dp)
                    .size(100.dp)
                    .padding(7.dp),
                contentAlignment = Alignment.Center,
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
}

/**
 * Composable function for rendering a preview of the SmallButton component.
 * This preview function is used for testing and visualizing the SmallButton component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewSmallButton() {
    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            SmallButton(SmallButtonConfiguration.ENGLISH)
            Spacer(modifier = Modifier.padding(5.dp))
            SmallButton(SmallButtonConfiguration.SPANISH)
        }
    }
}
