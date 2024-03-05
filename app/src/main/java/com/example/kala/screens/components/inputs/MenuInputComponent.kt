package com.example.kala.screens.components.inputs

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.kala.R
import com.example.kala.configuration.MenuInputConfiguration
import com.example.kala.configuration.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MenuInput(configuration: MenuInputConfiguration){
    var mExpanded by remember { mutableStateOf(false) }
    val options = configuration.getOptions()
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    var selectedImageResource: Int = R.drawable.ic_question

    val icon = if (mExpanded)
        R.drawable.ic_up_square
    else
        R.drawable.ic_down_square

    Row(
        modifier = Modifier
            .width(300.dp)
            .height(60.dp)
    ) {
        Column{
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .width(230.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    }
                ,
                label = {Text(text = configuration.getLabel(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray)},
                trailingIcon = {
                    Image(painter = painterResource(
                        id = icon
                    ),
                        contentDescription = "contentDescription",
                        modifier = Modifier
                            .size(35.dp)
                            .clickable { mExpanded = !mExpanded })

                }
            )

            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){
                        mTextFieldSize.width.toDp()}
                    )
                ,
            ) {
                options.forEach{
                        label ->
                    DropdownMenuItem(
                        text = { Text(text = label) },
                        onClick = {
                            mSelectedText = label
                            mExpanded = false
                            selectedImageResource = configuration.getSVG(label)
                        })
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .border(2.dp, Color.Black, shape = CircleShape)
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(2.dp)
                .padding(7.dp)
            ,
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(
                    id = selectedImageResource
                ),
                contentDescription = SVG_DESCRIPTION
            )
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun PreviewMenuInput() {
    Scaffold {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            items(MenuInputConfiguration.entries.toTypedArray()){
                    value ->
                MenuInput(value)
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }

}