package com.example.kala.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.configuration.LogoConfiguration
import com.example.kala.configuration.NAME_APPLICATION

@Composable
fun Logo(configuration: LogoConfiguration){
    Box(
        modifier = Modifier
            .background(Color.White)
            .size(configuration.getSize())
            .border(2.dp, Color.Black)
    ){
        Text(
            text = NAME_APPLICATION,
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            fontSize = 48.sp,
            textAlign = TextAlign.Center,

        )
    }
}

@Preview
@Composable
fun LogoPreview(){
    Column {
        Logo(LogoConfiguration.SMALL)
        Spacer(modifier = Modifier.padding(5.dp))
        Logo(LogoConfiguration.LARGE)
    }
}