package com.example.kala.screens.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.kala.configuration.LargeButtonConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.configuration.invalidArgument
import com.example.kala.ui.theme.BoneWhite

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LargeButton(configuration: LargeButtonConfiguration) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .width(300.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Button(
            onClick = {
                /* TODO */
                invalidArgument()
            },
            modifier = Modifier
                .height(60.dp)
                .width(300.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp)),
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
                        .border(2.dp, Color.Black, shape = CircleShape)
                        .clip(CircleShape)
                        .size(40.dp)
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

            }
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun LargeButtonPreview() {
    Scaffold {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            items(LargeButtonConfiguration.entries.toTypedArray()){
                value ->
                    LargeButton(configuration = value)
                Spacer(modifier = Modifier.padding(5.dp))

            }
        }
    }

}
