package com.example.kala.ui.components.popUps

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.kala.R
import com.example.kala.ui.theme.BoneWhite
import com.example.kala.ui.theme.Green1
import com.example.kala.ui.theme.Red0
import com.example.kala.ui.theme.dimens
import com.example.kala.ui.theme.fontFamily

val CONFIRMATION_TITLE_POP_UP = R.string.confirmation_title_pop_up
val CONFIRMATION_TEXT_POP_UP = R.string.confirmation_text_pop_up
val CONFIRMATION_BUTTON_POP_UP = R.string.confirmation_button_pop_up
val CONFIRMATION_DISMISS_POP_UP = R.string.confirmation_dismiss_pop_up

@Composable
fun ConfirmationPopUp(
    onConfirmButton: () -> Unit = {},
    onDismissButton: () -> Unit = {},
) {
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = {  },
        title = {
            Text(
                text = stringResource(id = CONFIRMATION_TITLE_POP_UP),
                color = Color.Black,
                fontSize = dimens.fontSize3,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
        },
        text = {
            Text(
                text = stringResource(id = CONFIRMATION_TEXT_POP_UP),
                color = Color.Black,
                fontSize = dimens.fontSize0,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(dimens.height2)
                        .width(dimens.width5)
                        .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
                    ,
                ) {
                    Button(
                        onClick = onConfirmButton,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
                        colors = ButtonDefaults.buttonColors(Green1),
                        shape = RoundedCornerShape(dimens.rounded),
                        contentPadding = PaddingValues(dimens.padding3)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(id = CONFIRMATION_BUTTON_POP_UP),
                                color = Color.Black,
                                fontSize = dimens.fontSize3,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        },
        dismissButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(dimens.height2)
                        .width(dimens.width5)
                        .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
                    ,
                ) {
                    Button(
                        onClick = onDismissButton,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
                        colors = ButtonDefaults.buttonColors(Red0),
                        shape = RoundedCornerShape(dimens.rounded),
                        contentPadding = PaddingValues(dimens.padding3)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(id = CONFIRMATION_DISMISS_POP_UP),
                                color = Color.Black,
                                fontSize = dimens.fontSize3,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    )

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ConfirmationButton() {
    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            ConfirmationPopUp()
        }
    }
}