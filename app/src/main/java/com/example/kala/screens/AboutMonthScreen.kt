package com.example.kala.screens

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import com.example.kala.configuration.ChartConfiguration
import com.example.kala.configuration.FooterConfiguration
import com.example.kala.configuration.HeaderConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.configuration.TitleConfiguration
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.screens.components.Chart
import com.example.kala.screens.components.Footer
import com.example.kala.screens.components.Header
import com.example.kala.screens.components.Title
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for rendering the About month screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutMonthScreen(
    navController: NavController? = null,
    moneyExchangeType: MoneyExchangeType? = null,
    currentMonth: String? = null,
){

    val titleConfiguration =
        if (moneyExchangeType == MoneyExchangeType.EXPENSE)
            TitleConfiguration.EXPENSE
        else
            TitleConfiguration.INCOME

    Scaffold(
        topBar = {
            Header(configuration = HeaderConfiguration.REGISTERED_USER, navController)
        },
        bottomBar = {
            Footer(configuration = FooterConfiguration.BACK_AND_HOME, navController)
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(50.dp))
            Title(configuration = titleConfiguration)
            Spacer(modifier = Modifier.padding(10.dp))
            Chart(configuration = ChartConfiguration.REPORT_PAGE)
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                ,
                contentAlignment = Alignment.Center
            ){
                LazyColumn{
                    items(
                        MoneyExchangeScope.entries.toTypedArray()
                    ){
                        value ->
                            val svgFile = MoneyExchangeScope.getSVGFile(value)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .padding(horizontal = 15.dp, vertical = 5.dp)
                                ,
                                Arrangement.SpaceBetween,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .border(2.dp, Color.Black, shape = CircleShape)
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .padding(5.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Image(
                                        painter = painterResource(id = svgFile),
                                        contentDescription = SVG_DESCRIPTION
                                    )
                                }
                                Text(text = value.toString(),
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Text(text = "10",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,)
                            }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}



/**
 * Composable function for previewing the About month screen.
 * This preview function is used for testing and visualizing the About month screen.
 */
@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun AboutMonthScreenPreview(){
    AboutMonthScreen()
}

