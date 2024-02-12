package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kala.configuration.DEFAULT_FLOAT
import com.example.kala.configuration.NavigationButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.configuration.invalidArgument

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationButton(
    configuration: NavigationButtonConfiguration,
    alpha: Float = DEFAULT_FLOAT
) {
    Button(
        onClick = {
            /* TODO */
            invalidArgument()
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

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun NavigationButtonPreview() {
    Column {
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