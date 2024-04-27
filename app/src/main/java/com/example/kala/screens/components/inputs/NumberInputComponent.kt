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
import com.example.kala.R
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens

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
            .width(dimens.width8)
    ){
        Text(
            text = "Value of the exchange",
            fontSize = dimens.fontSize1,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .height(dimens.height2)
            ,
        ) {
            TextField(
                value = valueInput,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = dimens.fontSize0,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .width(dimens.width5)
                    .height(dimens.height2)
                    .clip(RoundedCornerShape(dimens.rounded))
                    .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
                placeholder = {
                    Text(
                        text = "0.00",
                        style = TextStyle(
                            fontSize = dimens.fontSize0,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        ),
                    )
                }
            )
            Spacer(modifier = Modifier.size(dimens.space1))
            Box(
                modifier = Modifier
                    .border(dimens.border, Color.Black, shape = CircleShape)
                    .size(dimens.image4)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(dimens.padding2)
                ,
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
