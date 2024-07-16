package com.example.kala.ui.components.inputs

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.example.kala.R
import com.example.kala.ui.components.SVG_DESCRIPTION
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.dimens
import com.example.kala.ui.theme.fontFamily

/**
 * Composable function for rendering a menu input with customizable configuration.
 *
 * @param configuration The configuration for the menu input.
 * @param valueInput The current value of the menu input.
 * @param onValueChange Callback function to be executed when the value of the menu input changes.
 */
@Composable
fun MenuInput(
    configuration: MenuInputConfiguration,
    valueInput: Int,
    onValueChange: (String, Int) -> Unit
) {
    var mExpanded by remember { mutableStateOf(false) }
    val options = configuration.getOptions()
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedImageResource by remember { mutableIntStateOf(R.drawable.ic_question) }

    val icon = if (mExpanded)
        R.drawable.ic_up_square
    else
        R.drawable.ic_down_square

    Column(
        modifier = Modifier
            .width(dimens.width8)
    ) {
        Text(
            text = stringResource(id = configuration.getLabel()),
            fontSize = dimens.fontSize1,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimens.height2)
        ) {
            Column {
                OutlinedTextField(
                    value = stringResource(id = valueInput),
                    textStyle = TextStyle(
                        fontSize = dimens.fontSize0,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = fontFamily
                    ),
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier
                        .width(dimens.width5)
                        .clip(RoundedCornerShape(dimens.rounded))
                        .background(Color.White)
                        .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded))
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    placeholder = {
                        Text(
                            text = stringResource(id = configuration.getPlaceholder()),
                            fontSize = dimens.fontSize0,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { mExpanded = !mExpanded }
                        )
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = "contentDescription",
                            modifier = Modifier
                                .size(dimens.image1)
                                .clickable { mExpanded = !mExpanded }
                        )
                    }
                )

                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) {
                            mTextFieldSize.width.toDp()
                        })
                        .background(Color.White)
                ) {
                    options.forEach { label ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .background(Color.White),
                            text = {
                                Text(
                                    text = stringResource(id = label.second),
                                    style = TextStyle(
                                        fontSize = dimens.fontSize0,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        fontFamily = fontFamily
                                    ),
                                )
                            },
                            onClick = {
                                onValueChange(label.first, label.second)
                                mExpanded = false
                                selectedImageResource = configuration.getSVG(label.first)
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(dimens.space1))
            Box(
                modifier = Modifier
                    .border(dimens.border, Color.Black, shape = CircleShape)
                    .size(dimens.image4)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(dimens.padding2),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = selectedImageResource),
                    contentDescription = SVG_DESCRIPTION
                )
            }
        }
    }
}

/**
 * Composable function for rendering a preview of the MenuInput component.
 * This preview function is used for testing and visualizing the MenuInput component.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewMenuInput() {
    var enumExchange by remember { mutableStateOf("") }
    var showEnum by remember {
        mutableIntStateOf(R.string.empty)
    }

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            items(MenuInputConfiguration.entries.toTypedArray()) { value ->
                MenuInput(value, showEnum) { newValue, newShow ->
                    enumExchange = newValue
                    showEnum = newShow
                }
                Spacer(modifier = Modifier.padding(dimens.space0))
            }
        }
    }
}
