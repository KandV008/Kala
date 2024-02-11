package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.kala.R

const val ADD_EXCHANGE = "Add exchange"
const val CHANGE_NAME = "Change name"
const val CHANGE_EMAIL = "Change email"
const val SET_CURRENCY = "Set currency"
const val SIGN_UP = "Sign Up"
const val LOG_IN = "Log In"
const val LOG_OUT = "Log Out"
const val DELETE_USER = "Delete User"
const val FORGOT_PASS = "Forgot Pass?"
const val SEND_REQUEST = "Send request"

val svgLargeButton: Map<String, Int> = mapOf(
    ADD_EXCHANGE to R.drawable.ic_exchange,
    CHANGE_NAME to R.drawable.ic_account,
    CHANGE_EMAIL to R.drawable.ic_email,
    SET_CURRENCY to R.drawable.ic_currency,
    SIGN_UP to R.drawable.ic_sign_up,
    LOG_IN to R.drawable.ic_log_in,
    LOG_OUT to R.drawable.ic_log_out,
    DELETE_USER to R.drawable.ic_delete_account,
    FORGOT_PASS to R.drawable.ic_question,
    SEND_REQUEST to R.drawable.ic_next,
)

val actionsLargeButton: Map<String, () -> Unit> = mapOf(
    /* TODO Functions associate to a configuration */
)

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun LargeButton(configuration: String = "Sample Text") {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(60.dp)
            .width(300.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(10.dp),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = configuration,
                color = Color.Black,
                fontSize = 28.sp,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
            )

            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black, shape = CircleShape)
                    .clip(CircleShape)
                    .size(40.dp)
                    .clip(CircleShape)
                    .padding(7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = svgLargeButton.getOrDefault(configuration, -1)),
                    contentDescription = "SVG FILE"
                )
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun LargeButtonPreview() {
    Column {
        LargeButton(ADD_EXCHANGE)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(CHANGE_NAME)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(CHANGE_EMAIL)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(SET_CURRENCY)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(SIGN_UP)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LOG_IN)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(LOG_OUT)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(DELETE_USER)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(FORGOT_PASS)
        Spacer(modifier = Modifier.padding(5.dp))
        LargeButton(SEND_REQUEST)
    }

}
