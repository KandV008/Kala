package com.example.kala.screens.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.R
import com.example.kala.configuration.ChartConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.configuration.invalidArgument
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.Green0
import com.example.kala.ui.theme.Red0
import com.example.kala.ui.theme.Yellow0

@Composable
fun Chart(configuration: ChartConfiguration){
    Box(
        modifier = Modifier
            .height(370.dp)
            .width(320.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
        ,
    ){
        Column(
            modifier = Modifier
                .height(370.dp)
                .width(320.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                .padding(20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChartHeader(configuration)
            Spacer(modifier = Modifier.weight(1F))
            ChartBody(configuration)
            Spacer(modifier = Modifier.weight(1F))
            ChartFooter(configuration)
        }
    }
}

@Composable
fun ChartHeader(configuration: ChartConfiguration){
    Row(
        modifier = Modifier
            .height(50.dp)
            .width(250.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier
                .alpha(configuration.alpha())
                .size(40.dp)
                .shadow(10.dp, shape = CircleShape)
                .border(2.dp, Color.Black, CircleShape)
        ) {
            Button(
                onClick = {
                    /* TODO */
                    invalidArgument()
                },
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = CircleShape,
                modifier = Modifier
                    .size(40.dp)
                ,
                ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_previous
                    ),
                    contentDescription = SVG_DESCRIPTION
                )
            }
        }
        Text(
            text = "January",
            color = Color.Black,
            fontSize = 34.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Box(
            modifier = Modifier
                .alpha(configuration.alpha())
                .size(40.dp)
                .shadow(10.dp, shape = CircleShape)
                .border(2.dp, Color.Black, CircleShape)
        ) {
            Button(
                onClick = {
                    /* TODO */
                    invalidArgument()
                },
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = CircleShape,
                modifier = Modifier
                    .size(40.dp)
                ,
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_succesor
                    ),
                    contentDescription = SVG_DESCRIPTION,
                )
            }
        }
    }
}

@Composable
fun ChartBody(configuration: ChartConfiguration){
    /* TODO */
}

@Composable
fun ChartFooter(configuration: ChartConfiguration){
    Row(
        modifier = Modifier
            .height(80.dp)
            .width(300.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ChartInfo(title = "Income", value = "+185", color = Green0)
        ChartInfo(title = "Balance", value = "+35", color = Yellow0)
        ChartInfo(title = "Expense", value = "-150", color = Red0)
    }
}

@Composable
fun ChartInfo(title: String, value: String, color: Color){
    Column (
        modifier = Modifier
            .height(80.dp)
            .width(90.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ){
        Text(
            text = title,
            color = color,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            color = color,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun ChartPreview() {
    Scaffold {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            items(ChartConfiguration.entries.toTypedArray()){
                value ->
                    Chart(configuration = value)
                    Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }

}