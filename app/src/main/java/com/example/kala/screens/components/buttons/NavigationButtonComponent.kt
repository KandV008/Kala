package com.example.kala.screens.components.buttons

import android.annotation.SuppressLint
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
import com.example.kala.configuration.DEFAULT_FLOAT
import com.example.kala.configuration.NavigationButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

/**
 * Composable function for rendering a navigation button with customizable configuration.
 *
 * @param configuration The configuration for the navigation button.
 * @param alpha The alpha value to control the transparency of the button.
 * @param onAdviceTriggered Callback function to be executed when the button is clicked.
 */
@Composable
fun NavigationButton(
    configuration: NavigationButtonConfiguration,
    alpha: Float = DEFAULT_FLOAT,
    onAdviceTriggered: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(dimens.image4)
            .alpha(alpha)
            .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
    ){
        Button(
            onClick = onAdviceTriggered,
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(dimens.rounded),
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha),
            contentPadding = PaddingValues(dimens.padding3),
            border = BorderStroke(dimens.border, Color.Black),
            enabled = alpha > 0F
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
 * Composable function for rendering a preview of the NavigationButton component.
 * This preview function is used for testing and visualizing the NavigationButton component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
            Spacer(modifier = Modifier.padding(dimens.space0))
            NavigationButton(NavigationButtonConfiguration.HOME)
            Spacer(modifier = Modifier.padding(dimens.space0))
            NavigationButton(NavigationButtonConfiguration.HELP)
            Spacer(modifier = Modifier.padding(dimens.space0))
            NavigationButton(NavigationButtonConfiguration.BACK)
            Spacer(modifier = Modifier.padding(dimens.space0))
            NavigationButton(NavigationButtonConfiguration.NEXT)
            Spacer(modifier = Modifier.padding(dimens.space0))
            NavigationButton(NavigationButtonConfiguration.OPTIONS)
        }
    }
}
