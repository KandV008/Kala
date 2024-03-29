package com.example.kala.screens.components.inputs

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.ui.theme.BoneWhite

/**
 * Composable function for rendering a big text input field.
 *
 * @param valueInput The current value of the input field.
 * @param onValueChange Callback function to be executed when the value of the input field changes.
 */
@Composable
fun BigTextInput(valueInput: String, onValueChange: (String) -> Unit){
    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text
    )
    Column(
        modifier = Modifier
            .width(300.dp)
    ){
        Text(
            text = "Description",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )
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
                .width(300.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
            ,
            placeholder = {
                Text(
                    text = "Enter description...",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    ),
                )
            }
        )
    }
}

/**
 * Composable function for rendering a preview of the BigTextInput component.
 * This preview function is used for testing and visualizing the BigTextInput component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewBigTextInput() {
    var descriptionExchange by remember { mutableStateOf("") }

    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            BigTextInput(valueInput = descriptionExchange)
            { newValue -> descriptionExchange = newValue }
        }
    }
}
