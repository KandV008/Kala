package com.example.kala.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.configuration.TitleConfiguration

@Composable
fun Title(configuration: TitleConfiguration){
    Box(modifier = Modifier
        .height(50.dp)
        .width(280.dp)
        .border(2.dp, Color.Black)
        .background(Color.White),
        contentAlignment = Alignment.Center,
        ){
        Text(
            text = configuration.toString(),
            fontSize = 35.sp,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun TitlePreview(){
    Column {
        Title(TitleConfiguration.RECOVERY_PASS)
        Spacer(modifier = Modifier.padding(5.dp))
        Title(TitleConfiguration.CHANGE_PASS)
        Spacer(modifier = Modifier.padding(5.dp))
        Title(TitleConfiguration.HELP)
    }
}