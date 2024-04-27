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
import com.example.kala.configuration.MediumButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens
import com.example.kala.ui.theme.fontFamily

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
            .height(dimens.height5)
            .width(dimens.width3)
            .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
    ) {
        Button(
            onClick = {
                onAdviceTriggered()
            },
            modifier = Modifier
                .fillMaxSize()
                .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(dimens.rounded),
            contentPadding = PaddingValues(dimens.padding3),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .border(dimens.border, Color.Black, shape = CircleShape)
                        .padding(dimens.padding2)
                        .clip(CircleShape)
                        .size(dimens.image3)
                        .clip(CircleShape)
                        .padding(dimens.padding1),
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
                    fontSize = dimens.fontSize1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily
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
                Spacer(modifier = Modifier.padding(dimens.padding0))

            }
        }
    }
}
