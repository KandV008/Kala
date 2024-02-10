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
import androidx.compose.foundation.layout.fillMaxSize
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

const val seeReport = "spanish_language"
const val seeRecord = "english_language"
const val delete = "spanish_language"
const val edit = "english_language"
const val expense = "spanish_language"
const val income = "english_language"

val svgMediumButton: Map<String, Int> = mapOf(
    seeReport to R.drawable.ic_see_report,
    seeRecord to R.drawable.ic_see_record,
    delete to R.drawable.ic_delete,
    edit to R.drawable.ic_edit,
    expense to R.drawable.ic_expense,
    income to R.drawable.ic_income,
)

val actionsMediumButton: Map<String, () -> Unit> = mapOf(
    /* TODO Functions associate to a configuration */
)

val textMediumButton: Map<String, String> = mapOf(
    seeReport to "See Report",
    seeRecord to "See Record",
    delete to "Delete Card",
    edit to "Edit Card",
    expense to "Expense Details",
    income to "Income Details",
)


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MediumButton(configuration: String) {
    Button(
        onClick = {
            actionsMediumButton.getOrDefault(configuration)
            { println("NOT VALID CONFIGURATION") }
        },
        modifier = Modifier
            .height(150.dp)
            .width(130.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black, shape = CircleShape)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(50.dp)
                    .clip(CircleShape)
                    .padding(7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = svgMediumButton.getOrDefault(configuration, -1)),
                    contentDescription = "SVG FILE"
                )
            }
            Text(
                text = textMediumButton.getOrDefault(configuration, "Sample Text"),
                color = Color.Black,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun PreviewMediumButton() {
    Row {
        MediumButton(seeReport)
        Spacer(modifier = Modifier.padding(1.dp))
        MediumButton(seeRecord)
        Spacer(modifier = Modifier.padding(1.dp))
        MediumButton(delete)
        Spacer(modifier = Modifier.padding(1.dp))
    }
}