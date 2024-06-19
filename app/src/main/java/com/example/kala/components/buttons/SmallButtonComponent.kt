package com.example.kala.components.buttons

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
import com.example.kala.components.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering a small-sized button with customizable configuration.
 *
 * @param configuration The configuration for the small-sized button.
 */
@Composable
fun SmallButton(
    configuration: SmallButtonConfiguration,
    onAdviceTriggered: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(dimens.image5)
            .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
    ) {
        Button(
            onClick = {
                onAdviceTriggered(configuration.getAction())
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxSize()
                .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
                .background(Color.White, RoundedCornerShape(dimens.rounded)),
            contentPadding = PaddingValues(dimens.padding3)
        ) {
            Box(
                modifier = Modifier
                    .border(dimens.border, Color.Black, shape = CircleShape)
                    .padding(dimens.padding2)
                    .fillMaxSize()
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
            SmallButton(SmallButtonConfiguration.ENGLISH){}
            Spacer(modifier = Modifier.padding(dimens.space0))
            SmallButton(SmallButtonConfiguration.SPANISH){}
        }
    }
}
