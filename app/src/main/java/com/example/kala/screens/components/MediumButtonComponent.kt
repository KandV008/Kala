package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.configuration.DEFAULT_INT
import com.example.kala.configuration.MediumButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.configuration.actionsMediumButton
import com.example.kala.configuration.invalidArgument
import com.example.kala.configuration.svgMediumButton


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MediumButton(configuration: MediumButtonConfiguration) {
    Button(
        onClick = {
            actionsMediumButton.getOrDefault(configuration)
            { invalidArgument() }
        },
        modifier = Modifier
            .height(150.dp)
            .width(130.dp),
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
                    .border(1.dp, Color.Black, shape = CircleShape)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(50.dp)
                    .clip(CircleShape)
                    .padding(7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(
                        id = svgMediumButton
                            .getOrDefault(configuration, DEFAULT_INT)
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

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun PreviewMediumButton() {
    Column {
        MediumButton(MediumButtonConfiguration.SEE_REPORT)
        Spacer(modifier = Modifier.padding(5.dp))
        MediumButton(MediumButtonConfiguration.SEE_RECORD)
        Spacer(modifier = Modifier.padding(5.dp))
        MediumButton(MediumButtonConfiguration.DELETE)
        Spacer(modifier = Modifier.padding(5.dp))
        MediumButton(MediumButtonConfiguration.EDIT)
        Spacer(modifier = Modifier.padding(5.dp))
        MediumButton(MediumButtonConfiguration.EXPENSE)
        Spacer(modifier = Modifier.padding(5.dp))
        MediumButton(MediumButtonConfiguration.INCOME)
        Spacer(modifier = Modifier.padding(5.dp))
    }
}