package com.example.kala.components.popups

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.kala.ui.theme.dimens
import com.example.kala.ui.theme.fontFamily

val INVALID_FORM_TITLE_POP_UP = R.string.invalid_form_title_pop_up
val INVALID_FORM_BUTTON_POP_UP = R.string.invalid_form_button_pop_up

@Composable
fun InvalidFormPopUp(
    messageList: MutableList<Int>,
    onConfirmButton: () -> Unit,
) {
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = {  },
        title = {
            Text(
                text = stringResource(id = INVALID_FORM_TITLE_POP_UP),
                color = Color.Black,
                fontSize = dimens.fontSize3,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimens.padding2)
            ) {
                messageList.forEach { errorMessage ->
                    Text(
                        text = "• " + stringResource(id = errorMessage),
                        color = Color.Black,
                        fontSize = dimens.fontSize0,
                        textAlign = TextAlign.Start,
                        fontFamily = fontFamily
                    )
                    Spacer(modifier = Modifier.padding(dimens.space0))
                }
            }
        },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(dimens.height2)
                        .width(dimens.width5)
                        .shadow(dimens.shadow, shape = RoundedCornerShape(dimens.rounded))
                        .align(Alignment.CenterHorizontally)
                    ,
                ) {
                    Button(
                        onClick = onConfirmButton,
                        modifier = Modifier
                            .fillMaxSize()
                            .border(dimens.border, Color.Black, RoundedCornerShape(dimens.rounded)),
                        colors = ButtonDefaults.buttonColors(Color.White),
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
                                text = stringResource(id = INVALID_FORM_BUTTON_POP_UP),
                                color = Color.Black,
                                fontSize = dimens.fontSize3,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        },
    )

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun InvalidFormPopUpButton() {

    val exampleList: MutableList<Int> = mutableListOf()

    Scaffold {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(BoneWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            InvalidFormPopUp(exampleList){}
        }
    }
}