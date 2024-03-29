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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.configuration.MediumButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for rendering a medium-sized button with customizable configuration.
 *
 * @param configuration The configuration for the medium-sized button.
 * @param onAdviceTriggered Callback function to be executed when the button is clicked.
 */
@Composable
fun MediumButton(
    configuration: MediumButtonConfiguration,
    onAdviceTriggered: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(130.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Button(
            onClick = {
                onAdviceTriggered()
            },
            modifier = Modifier
                .height(150.dp)
                .width(130.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(10.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .border(2.dp, Color.Black, shape = CircleShape)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .size(50.dp)
                        .clip(CircleShape)
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
                Text(
                    text = configuration.toString(),
                    color = Color.Black,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

/**
 * Composable function for rendering a preview of the MediumButton component.
 * This preview function is used for testing and visualizing the MediumButton component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewMediumButton() {
    Scaffold {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            items(MediumButtonConfiguration.entries.toTypedArray()){
                    value ->
                MediumButton(configuration = value, onAdviceTriggered = {})
                Spacer(modifier = Modifier.padding(5.dp))

            }
        }
    }
}
