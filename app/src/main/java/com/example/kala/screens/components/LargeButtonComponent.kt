package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.kala.screens.configuration.DEFAULT_INT
import com.example.kala.screens.configuration.LargeButtonConfiguration
import com.example.kala.screens.configuration.SVG_DESCRIPTION
import com.example.kala.screens.configuration.actionsLargeButton
import com.example.kala.screens.configuration.invalidArgument
import com.example.kala.screens.configuration.svgLargeButton


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LargeButton(configuration: LargeButtonConfiguration) {
    Button(
        onClick = {
                  actionsLargeButton
                      .getOrDefault(configuration)
                      { invalidArgument() }
        },
        modifier = Modifier
            .height(60.dp)
            .width(300.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(10.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = configuration.toString(),
                color = Color.Black,
                fontSize = 28.sp,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
            )

            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black, shape = CircleShape)
                    .clip(CircleShape)
                    .size(40.dp)
                    .clip(CircleShape)
                    .padding(7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(
                        id = svgLargeButton
                            .getOrDefault(configuration, DEFAULT_INT)
                    ),
                    contentDescription = SVG_DESCRIPTION
                )
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun LargeButtonPreview() {
    Column {
        LargeButton(LargeButtonConfiguration.ADD_EXCHANGE)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.CHANGE_NAME)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.CHANGE_EMAIL)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.SET_CURRENCY)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.SIGN_UP)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.LOG_IN)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.LOG_OUT)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.DELETE_USER)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.FORGOT_PASS)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LargeButtonConfiguration.SEND_REQUEST)
    }

}
