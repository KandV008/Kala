package com.example.kala.screens.components.inputs

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.R
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for rendering a number input field.
 *
 * @param valueInput The current value of the number input field.
 * @param onValueChange Callback function to be executed when the value of the number input field changes.
 */
@Composable
fun NumberInput(valueInput: String, onValueChange: (String) -> Unit){
    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number
    )

    Column(
        modifier = Modifier
            .width(300.dp)
    ){
        Text(
            text = "Value of the exchange",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .height(60.dp)
            ,
        ) {
            TextField(
                value = valueInput,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .width(230.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp)),
                placeholder = {
                    Text(
                        text = "0.00",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        ),
                    )
                }
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Black, shape = CircleShape)
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(2.dp)
                    .padding(7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_currency
                    ),
                    contentDescription = SVG_DESCRIPTION
                )
            }
        }
    }
}

/**
 * Composable function for rendering a preview of the NumberInput component.
 * This preview function is used for testing and visualizing the NumberInput component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewNumberInput() {
    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            var valueExchange by remember { mutableStateOf("0.00") }
            NumberInput(valueInput = valueExchange) { newValue ->
                valueExchange = newValue
            }
        }
    }
}
