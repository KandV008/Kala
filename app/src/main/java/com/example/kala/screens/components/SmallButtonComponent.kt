package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kala.screens.configuration.DEFAULT_INT
import com.example.kala.screens.configuration.SVG_DESCRIPTION
import com.example.kala.screens.configuration.SmallButtonConfiguration
import com.example.kala.screens.configuration.actionsSmallButton
import com.example.kala.screens.configuration.invalidArgument
import com.example.kala.screens.configuration.svgSmallButton


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SmallButton(configuration: SmallButtonConfiguration) {
    Button(
        onClick = {
            actionsSmallButton
                .getOrDefault(configuration)
                { invalidArgument() }
        },
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .size(100.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, Color.Black, shape = CircleShape)
                .padding(8.dp)
                .clip(CircleShape)
                .size(100.dp)
                .clip(CircleShape)
                .padding(7.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(
                    id = svgSmallButton
                        .getOrDefault(configuration, DEFAULT_INT)
                ),
                contentDescription = SVG_DESCRIPTION
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun PreviewSmallButton() {
    Column {
        SmallButton(SmallButtonConfiguration.ENGLISH)
        Spacer(modifier = Modifier.padding(5.dp))
        SmallButton(SmallButtonConfiguration.SPANISH)
    }
}